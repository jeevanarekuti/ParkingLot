package main.models;

import java.util.List;

public class Floor {
    private BaseModel baseModel;
    private List<Spot> spots;
    private int floorNum;

    public Floor(BaseModel baseModel, List<Spot> spots, int floorNum) {
        this.baseModel = baseModel;
        this.spots = spots;
        this.floorNum = floorNum;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public int getFloorNum() {
        return floorNum;
    }
}
