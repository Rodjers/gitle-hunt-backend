package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Observation;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Welcome to Play"));
    }

    public static Result registerObservation(){
        Http.RequestBody body = request().body();

        JsonNode jsonBody = body.asJson();

        Observation observation = new Observation();

        observation.amount = jsonBody.findPath("amount").intValue();
        observation.animal = jsonBody.findPath("animal").textValue();
        observation.longitude = jsonBody.findPath("longitude").longValue();
        observation.latitude = jsonBody.findPath("latitude").longValue();

        observation.save();

        return ok("Saved this: " + observation.asJson());

    }

    public static Result getObservations(){
        return ok(Observation.all().toString());
    }

}
