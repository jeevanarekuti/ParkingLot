package main.strategy.spotAssignment;

import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.models.Gate;
import main.models.Spot;
import main.models.Vehicle;

public interface SpotAssignmentStrategy {
    public Spot assignSpot(Vehicle vehicle, Gate gate) throws NoParkingSpotsFoundException, ParkingLotDoesnotExist;
}
