package main.strategy.pricing_strategy;

import main.models.BasePrice;
import main.models.PriceType;
import main.models.Slab;
import main.models.VehicleType;
import main.repositories.BasePriceRepository;
import main.repositories.SlabRepository;
import main.utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy{

    private BasePriceRepository basePriceRepository;
    private SlabRepository slabRepository;

    public WeekendPricingStrategy(SlabRepository slabRepository, BasePriceRepository basePriceRepository) {
        this.slabRepository = slabRepository;
        this.basePriceRepository = basePriceRepository;
    }


    @Override
    public double calculateAmount(VehicleType vehicleType, Date entryTime, Date exitTime) {
        BasePrice basePrice = basePriceRepository.getBasePriceByVehicleAndPriceType(vehicleType, PriceType.WEEKEND);
        List<Slab> slabs = slabRepository.getSlabsByVehicleAndPriceType(vehicleType, PriceType.WEEKEND);
        double amount = 0;
        int numberOfHoursSpent = DateTimeUtils.numberOfHoursSpent(entryTime, exitTime);
        for (Slab slab : slabs) {
            if(numberOfHoursSpent >  slab.getEndHour()){
                amount += (slab.getEndHour() - slab.getStartHour()) * slab.getMultiplier() * basePrice.getAmount();
            } else if(slab.getEndHour() == -1 || numberOfHoursSpent <= slab.getEndHour()) {
                amount += (numberOfHoursSpent - slab.getStartHour()) * slab.getMultiplier() * basePrice.getAmount();
                break;
            }
        }
        return amount;
    }
}
