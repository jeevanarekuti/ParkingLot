package main.models;

import java.util.Date;

public class Ticket {
    private BaseModel baseModel;
    private String vehicleNumber;
    private Spot assingedSpot;
    private Date entryTime;

    public Ticket(String vehicleNumber, Spot assingedSpot, Date entryTime, BaseModel baseModel) {
        this.vehicleNumber = vehicleNumber;
        this.assingedSpot = assingedSpot;
        this.entryTime = entryTime;
        this.baseModel = baseModel;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public Spot getAssingedSpot() {
        return assingedSpot;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "baseModel=" + baseModel +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", assingedSpot=" + assingedSpot +
                ", entryTime=" + entryTime +
                '}';
    }
}
