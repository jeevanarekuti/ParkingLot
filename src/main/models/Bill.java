package main.models;

import java.util.Date;
import java.util.List;

public class Bill {
    private BaseModel baseModel;
    private double amount;
    Date entrytime;
    Date exittime;
    Vehicle vehicle;
    List<Payment> payments;

    public Bill(BaseModel baseModel, double amount, Date entrytime, Date exittime, Vehicle vehicle) {
        this.baseModel = baseModel;
        this.amount = amount;
        this.entrytime = entrytime;
        this.exittime = exittime;
        this.vehicle = vehicle;
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "baseModel=" + baseModel +
                ", amount=" + amount +
                ", entrytime=" + entrytime +
                ", exittime=" + exittime +
                ", vehicle=" + vehicle +
                ", payments=" + payments +
                '}';
    }
}
