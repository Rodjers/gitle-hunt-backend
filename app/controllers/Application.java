package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.exceptions.InvalidObservationException;
import models.Observation;
import org.omg.CORBA.DynAnyPackage.Invalid;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("GitleHunt"));
    }



    public static Result registerObservation(){
        Http.RequestBody body = request().body();

        JsonNode jsonBody = body.asJson();


        int amount = jsonBody.findPath("amount").intValue();
        String animal = jsonBody.findPath("animal").textValue();
        double longitude = jsonBody.findPath("longitude").doubleValue();
        double latitude = jsonBody.findPath("latitude").doubleValue();

        Observation observation = new Observation(amount, animal, longitude, latitude);

        observation.save();

        return ok("Saved this: " + observation.asJson());

    }

    public static Result getObservations(){
        return ok(Observation.all().toString());
    }

}
