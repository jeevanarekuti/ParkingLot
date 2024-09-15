package main.strategy.pricing_strategy;

import main.models.VehicleType;

import java.util.Date;

public class WeekdayPricingStrategy implements PricingStrategy{
    @Override
    public double calculateAmount(VehicleType vehicleType, Date entryTime, Date exitTime) {
        return 0;
    }
}
