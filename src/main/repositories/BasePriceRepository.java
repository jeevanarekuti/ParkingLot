package main.repositories;

import main.models.BasePrice;
import main.models.PriceType;
import main.models.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class BasePriceRepository {
    private Map<Integer, BasePrice> map;

    public BasePriceRepository() {
        map = new HashMap<>();
    }

    public BasePriceRepository(Map<Integer, BasePrice> map) {
        this.map = map;
    }

    public BasePrice getBasePriceByVehicleAndPriceType(VehicleType vehicleType, PriceType priceType){
        for (Map.Entry<Integer, BasePrice> entry : map.entrySet()) {
            BasePrice basePrice = entry.getValue();
            if(basePrice.getPriceType().equals(priceType) && basePrice.getVehicleType().equals(vehicleType)){
                return basePrice;
            }
        }
        return null;
    }
}
