package main.services;

import main.models.Vehicle;
import main.models.VehicleType;
import main.repositories.VehicleRepository;

public class VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle getVehicleByNumber(String VehicleNumber){
        return vehicleRepository.getVehicleByNumber(VehicleNumber);
    }

    public Vehicle insertVehicle(String vehicleNumber, VehicleType vehicleType){
        return vehicleRepository.insertVehicle(vehicleNumber,vehicleType);
    }

    public Vehicle insertIfNotExists(String vehicleNumber,VehicleType vehicleType){
        Vehicle vehicle = getVehicleByNumber(vehicleNumber);
        if(vehicle == null){
            vehicle = insertVehicle(vehicleNumber,vehicleType);
            return vehicle;
        }
        return null;
    }
}


