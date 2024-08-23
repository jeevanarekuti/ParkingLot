package main.repositories;

import main.models.Gate;
import main.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {
    private Map<Integer , ParkingLot> map;

    public ParkingLotRepository(){
        map = new HashMap<>();
    }

    public ParkingLotRepository(Map<Integer, ParkingLot> map) {
        this.map = map;
    }

    public ParkingLot getParkingLotByGateId(int gateId){
        for (Map.Entry<Integer, ParkingLot> entry : map.entrySet()) {
            ParkingLot parkingLot = entry.getValue();
            for (Gate entryGate : parkingLot.getEntryGates()) {
                if(entryGate.getBaseModel().getId() == gateId){
                    return parkingLot;
                }
            }
        }
        return null;
    }
}
