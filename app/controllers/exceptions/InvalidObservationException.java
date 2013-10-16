package controllers.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: oddgeir
 * Date: 10/10/13
 * Time: 11:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidObservationException extends Exception {

    public InvalidObservationException(String message){
        super(message);
    }

    public InvalidObservationException(int size, int expectedSize){
        super("Unexpected number of parameters. " + size + " received. Expected " + expectedSize);
    }
}
