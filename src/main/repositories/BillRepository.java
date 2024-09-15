package main.repositories;

import main.models.BaseModel;
import main.models.Bill;
import main.models.Vehicle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BillRepository {

    private Map<Integer, Bill> bills;

    public BillRepository(Map<Integer, Bill> bills) {
        this.bills = bills;
    }

    public BillRepository(){
        bills = new HashMap<>();
    }

    private static int idSequence = 1;

    public Bill createBill(Date entryTime, Date exitTime, double amount, Vehicle vehicle){
        BaseModel baseModel = new BaseModel(idSequence);
        Bill bill = new Bill(baseModel, amount, entryTime, exitTime, vehicle);
        bills.put(idSequence, bill);
        idSequence++;
        return bill;
    }
}
