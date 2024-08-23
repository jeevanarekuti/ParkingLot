package main.strategy.spotAssignment;

import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.models.*;
import main.repositories.ParkingLotRepository;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{

    private ParkingLotRepository repository;

    public NearestSpotAssignmentStrategy(ParkingLotRepository repository) {
        this.repository = repository;
    }

    @Override
    public Spot assignSpot(VehicleType vehicleType, Gate gate) throws NoParkingSpotsFoundException, ParkingLotDoesnotExist {
        ParkingLot parkingLot = repository.getParkingLotByGateId(gate.getBaseModel().getId());
        if(parkingLot!=null){
            return getAvailableSpot(parkingLot,vehicleType);
        }
        throw new ParkingLotDoesnotExist();
    }

    private Spot getAvailableSpot(ParkingLot parkingLot, VehicleType vehicleType) throws NoParkingSpotsFoundException{
        for(Floor floor : parkingLot.getFloor()){
            for(Spot spot: floor.getSpots()){
                if((spot.getStatus() == SpotStatus.UNOCCUPIED) && spot.getSupportedVehicleType()==vehicleType){
                    return spot;
                }
            }
        }
        throw new NoParkingSpotsFoundException("No parking spots available for: " + vehicleType.name());
    }
}
