package main.models;

public class Vehicle {
    private BaseModel baseModel;
    private VehicleType vehicleType;
    private String vehicleNumber;

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}
