package main.models;

public class Spot {

    private BaseModel baseModel;
    private String spotName;
    private SpotStatus status;
    private Vehicle vehicle;
    private VehicleType supportedVehicleType;

    public Spot(BaseModel baseModel, String spotName, VehicleType supportedVehicleType) {
        this.baseModel = baseModel;
        this.spotName = spotName;
        this.status = SpotStatus.UNOCCUPIED;
        this.supportedVehicleType = supportedVehicleType;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public String getSpotName() {
        return spotName;
    }

    public SpotStatus getStatus() {
        return status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    public VehicleType getSupportedVehicleType() {
        return supportedVehicleType;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "baseModel=" + baseModel +
                ", spotName='" + spotName + '\'' +
                ", status=" + status +
                ", vehicle=" + vehicle +
                ", supportedVehicleType=" + supportedVehicleType +
                '}';
    }

    public void allocateSpot(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.status = SpotStatus.OCCUPIED;
    }
}
