package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.exceptions.InvalidObservationException;
import play.libs.Json;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Author: oddgeir
 * Date: 10/9/13
 * Time: 7:35 PM
 */
@Entity
public class Observation extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    @Column(nullable = false)
    public int amount;

    @Column(nullable = false)
    public String animal;

    @Column(nullable = false)
    public double longitude;

    @Column(nullable = false)
    public double latitude;

    @Formats.DateTime(pattern="yyyy-MM-dd")
    public String date;

    @Formats.DateTime(pattern="HH:mm:ss")
    public String time;

    public Observation(JsonNode observation) throws InvalidObservationException {

        if(observation.size() > 6){
            throw new InvalidObservationException(observation.size(), 6);
        }

        if(observation.has("amount")){
            this.amount = observation.findPath("amount").intValue();
        }
        else {
            throw new InvalidObservationException("Parameter \'amount\' is missing");
        }

        if(observation.has("animal")){
            this.animal = observation.findPath("animal").textValue();
        }
        else {
            throw new InvalidObservationException("Parameter \'animal\' is missing");
        }

        if(observation.has("latitude")){
            this.latitude = observation.findPath("latitude").doubleValue();
        }
        else {
            throw new InvalidObservationException("Parameter \'latitude\' is missing");
        }

        if(observation.has("longitude")){
            this.longitude = observation.findPath("longitude").doubleValue();
        }
        else {
            throw new InvalidObservationException("Parameter \'longitude\' is missing");
        }

        if(observation.has("date")){
            String _timeStamp = observation.findPath("date").textValue();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            try {
                date = dateFormat.parse(_timeStamp);
            } catch (ParseException e) {
                throw new InvalidObservationException(_timeStamp + "is an invalid date format. Expected: yyy-MM-dd");
            }
            this.date = dateFormat.format(date);
        }
        else {
            throw new InvalidObservationException("Parameter \'timestamp\' is missing");
        }



        if(observation.has("time")){
            String _timeStamp = observation.findPath("time").textValue();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date;
            try {
                date = dateFormat.parse(_timeStamp);
            } catch (ParseException e) {
                throw new InvalidObservationException(_timeStamp + "is an invalid time format. Expected: HH:mm:ss");
            }
            this.time = dateFormat.format(date);
        }
        else {
            throw new InvalidObservationException("Parameter \'timestamp\' is missing");
        }



    }

    public static Finder<Long,Observation> find = new Finder<Long,Observation>(Long.class, Observation.class);

    public static ObjectNode all(){

        List<Observation> observationList = find.findList();

        ObjectNode result = Json.newObject();

        Iterator<Observation> it = observationList.iterator();
        int i = 0;
        while(it.hasNext()){
            result.put(Integer.toString(i), it.next().asJson());
            i++;
        }

        return result;
    }

    public static ObjectNode getSquare(double north, double west, double south, double east){

        List observationList = find
                .where().gt("longitude", west)
                .where().lt("longitude", east)
                .where().gt("latitude", south)
                .where().lt("latitude", north)
                .findList();

        ObjectNode result = Json.newObject();
        @SuppressWarnings("unchecked")
        Iterator<Observation> it = observationList.iterator();
        int i = 0;
        while(it.hasNext()){
//            result.putAll(it.next().asJson());
            result.put(Integer.toString(i), it.next().asJson());
            i++;
        }

        return result;
    }

    public ObjectNode asJson(){

        ObjectNode result = Json.newObject();

        result.put("id", this.id);
        result.put("amount", this.amount);
        result.put("animal", this.animal);
        result.put("longitude", this.longitude);
        result.put("latitude", this.latitude);
        result.put("date", this.date);
        result.put("time", this.time);



        return result;
    }
}
