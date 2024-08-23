package main.models;

import java.util.List;

public class ParkingLot {

    private BaseModel baseModel;
    List<Floor> floor;
    List<Gate>entryGates;
    List<ExitGate>exitGates;

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public List<Floor> getFloor() {
        return floor;
    }

    public List<Gate> getEntryGates() {
        return entryGates;
    }

    public List<ExitGate> getExitGates() {
        return exitGates;
    }

    public ParkingLot(BaseModel baseModel, List<Floor> floor, List<Gate> entryGates) {
        this.floor = floor;
        this.baseModel = baseModel;
        this.entryGates = entryGates;
    }
}
