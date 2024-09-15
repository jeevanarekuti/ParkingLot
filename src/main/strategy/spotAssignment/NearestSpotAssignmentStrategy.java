package main.strategy.spotAssignment;

import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.models.*;
import main.repositories.ParkingLotRepository;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{


    @Override
    public Spot assignSpot(Vehicle vehicle, Gate gate, ParkingLotRepository parkingLotRepository) throws NoParkingSpotsFoundException, ParkingLotDoesnotExist {
        //TODO read and implement template Pattern here.
        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateId(gate.getBaseModel().getId());
        if(parkingLot!=null){
            Spot assignedSpot =  getAvailableSpot(parkingLot,vehicle.getVehicleType());
            assignedSpot.allocateSpot(vehicle);
            return assignedSpot;
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
