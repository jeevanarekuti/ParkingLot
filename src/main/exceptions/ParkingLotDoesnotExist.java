package main.exceptions;

public class ParkingLotDoesnotExist extends Exception{
    public ParkingLotDoesnotExist(){
        System.out.println("Parking Lot doesn't exists!");
    }
}
