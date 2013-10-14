package models;

import com.avaje.ebean.Page;
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
 * Created with IntelliJ IDEA.
 * User: oddgeir
 * Date: 10/9/13
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
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

    @Formats.DateTime(pattern="yyyy-MM-dd HH:mm:ss")
    public String timestamp;

    public Observation(int amount, String animal, double longitude, double latitude){

        this.amount = amount;
        this.animal = animal;
        this.longitude = longitude;
        this.latitude = latitude;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = new Date();
        this.timestamp = dateFormat.format(date);

    }
    public Observation(JsonNode observation) throws InvalidObservationException {

        if(observation.size() != 4){
            throw new InvalidObservationException(observation.size(), 4);
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

    public ObjectNode asJson(){

        ObjectNode result = Json.newObject();

        result.put("id", this.id);
        result.put("amount", this.amount);
        result.put("animal", this.animal);
        result.put("longitude", this.longitude);
        result.put("latitude", this.latitude);
        Date date = null;
        try {
            DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-1"));
            date = dateFormat.parse(this.timestamp);
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if(date != null) {
            result.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        }



        return result;
    }
}
