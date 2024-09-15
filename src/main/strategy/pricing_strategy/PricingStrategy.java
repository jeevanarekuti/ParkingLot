package main.strategy.pricing_strategy;

import main.models.VehicleType;

import java.util.Date;

public interface PricingStrategy {
    public double calculateAmount(VehicleType vehicleType, Date entryTime, Date exitTime);
}
