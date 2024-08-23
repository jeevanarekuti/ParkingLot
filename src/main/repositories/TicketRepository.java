package main.repositories;

import main.models.BaseModel;
import main.models.Spot;
import main.models.Ticket;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Integer, Ticket> map;

    private int idSequence = 0;

    public TicketRepository(){
        map = new HashMap<>();
    }

    public Ticket createTicket(String vehicleNumber, Spot assignedSpot, Date entryTime){
        BaseModel baseModel = new BaseModel(idSequence);
        Ticket ticket = new Ticket(vehicleNumber,assignedSpot,entryTime,baseModel);
        map.put(idSequence,ticket);
        idSequence++;
        return ticket;
    }


}
