package main.services;

import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.exceptions.GateNotFoundException;
import main.models.*;
import main.repositories.GateRepository;
import main.repositories.TicketRepository;
import main.strategy.spotAssignment.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {

    private SpotAssignmentStrategy spotAssignmentStrategy;
    private GateService gateService;
    private TicketRepository ticketRepository;

    public TicketService(SpotAssignmentStrategy spotAssignmentStrategy, GateService gateService, TicketRepository ticketRepository) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.gateService = gateService;
        this.ticketRepository = ticketRepository;
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, int gateId) throws ParkingLotDoesnotExist, NoParkingSpotsFoundException, GateNotFoundException {

        Gate gate = gateService.getGateById(gateId);

        if(gate == null){
            throw new GateNotFoundException();
        }

        Spot spot;

        try {
            spot = spotAssignmentStrategy.assignSpot(vehicleType,gate);
        } catch (Exception e) {
            throw e;
        }

        //TODO once spot is finalized set status of spot as OCCUPIED.
        spot.setStatus(SpotStatus.OCCUPIED);


        Date entryTime = new Date();

        Ticket ticket = ticketRepository.createTicket(vehicleNumber,spot,entryTime);
        //System.out.println("Ticket:" + ticket);
        return ticket;
    }
}
