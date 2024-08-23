package main.exceptions;

public class NoParkingSpotsFoundException extends Exception{
    public NoParkingSpotsFoundException(String message){
        super(message);
    }
}
