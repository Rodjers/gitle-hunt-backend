package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.exceptions.InvalidObservationException;
import models.Observation;
import play.mvc.*;
import views.html.index;
import views.html.observations;

import java.util.Map;

/**
 * Author: oddgeir
 * Date: 10/9/13
 * Time: 7:35 PM
 */

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("GitleHunt"));
    }

    public static Result showObservations() {
        return ok(observations.render("GitleHunt"));
    }



    public static Result registerObservation(){
        Http.RequestBody body = request().body();

        JsonNode jsonBody = body.asJson();

        Observation observation = null;
        try {
            observation = new Observation(jsonBody);
        } catch (InvalidObservationException e) {
            return badRequest(e.getMessage());
        }

        observation.save();

        return ok("Saved this: " + observation.asJson());

    }

    public static Result getObservations(){
        return ok(Observation.all().toString());
    }

    public static Result getSquare(String north, String west, String south, String east){

        double _north;
        double _west;
        double _south;
        double _east;

        try {
            _north = Double.valueOf(north);
        }
        catch (Exception e){
                return badRequest("parameter north is invalid");
        }

        try {
            _west = Double.valueOf(west);
        }
        catch (Exception e){
            return badRequest("parameter west is invalid");
        }

        try {
            _south = Double.valueOf(south);
        }
        catch (Exception e){
            return badRequest("parameter south is invalid");
        }

        try {
            _east = Double.valueOf(east);
        }
        catch (Exception e){
            return badRequest("parameter east is invalid");
        }

        ObjectNode result = Observation.getSquare(_north, _west, _south, _east);



        return ok(result.toString());
    }

    public static Result wrongMethod(){
        return status(METHOD_NOT_ALLOWED);
    }

}
