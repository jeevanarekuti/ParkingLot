package main.factories;

import main.strategy.pricing_strategy.PricingStrategy;
import main.strategy.pricing_strategy.WeekdayPricingStrategy;
import main.strategy.pricing_strategy.WeekendPricingStrategy;
import main.utils.DateTimeUtils;
import main.repositories.BasePriceRepository;
import main.repositories.SlabRepository;

import java.util.Date;

public class PricingStrategyFactory {
    private BasePriceRepository basePriceRepository;
    private SlabRepository slabRepository;

    public PricingStrategyFactory(BasePriceRepository basePriceRepository, SlabRepository slabRepository) {
        this.basePriceRepository = basePriceRepository;
        this.slabRepository = slabRepository;
    }

    public PricingStrategy getPricingStrategy(Date endTime){
        return DateTimeUtils.isWeekend(endTime) ? new WeekendPricingStrategy(slabRepository, basePriceRepository) : new WeekdayPricingStrategy();
    }

}
