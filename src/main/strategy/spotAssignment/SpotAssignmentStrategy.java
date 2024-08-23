package main.strategy.spotAssignment;

import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.models.Gate;
import main.models.Spot;
import main.models.VehicleType;

public interface SpotAssignmentStrategy {
    public Spot assignSpot(VehicleType vehicleType, Gate gate) throws NoParkingSpotsFoundException, ParkingLotDoesnotExist;
}
