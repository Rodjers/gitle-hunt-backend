package models;

import com.avaje.ebean.Page;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


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

    public int amount;

    public String animal;

    public long longitude;

    public long latitude;

    @Formats.DateTime(pattern="yyyy-MM-dd-HH-mm-ss")
    public Date timestamp;

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

        result.put("amount", this.amount);
        result.put("animal", this.animal);
        result.put("longitude", this.longitude);
        result.put("latitude", this.latitude);

        return result;
    }
}
