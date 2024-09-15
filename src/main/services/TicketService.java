package main.services;

import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.exceptions.GateNotFoundException;
import main.models.*;
import main.repositories.BillRepository;
import main.repositories.GateRepository;
import main.repositories.TicketRepository;
import main.strategy.pricing_strategy.PricingStrategy;
import main.strategy.spotAssignment.SpotAssignmentStrategy;
import main.factories.PricingStrategyFactory;

import java.util.Date;

public class TicketService {

    private SpotAssignmentStrategy spotAssignmentStrategy;
    private GateService gateService;
    private TicketRepository ticketRepository;
    private VehicleService vehicleService;
    private PricingStrategyFactory pricingStrategyFactory;
    BillRepository billRepository;


    public TicketService(SpotAssignmentStrategy spotAssignmentStrategy, GateService gateService, TicketRepository ticketRepository, VehicleService vehicleService, PricingStrategyFactory pricingStrategyFactory, BillRepository billRepository) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.gateService = gateService;
        this.ticketRepository = ticketRepository;
        this.vehicleService = vehicleService;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.billRepository = billRepository;
    }

    public TicketService(SpotAssignmentStrategy spotAssignmentStrategy, GateService gateService, TicketRepository ticketRepository, VehicleService vehicleService) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.gateService = gateService;
        this.ticketRepository = ticketRepository;
        this.vehicleService = vehicleService;
    }

    public Ticket generateTicket(String vehicleNumber, VehicleType vehicleType, int gateId) throws ParkingLotDoesnotExist, NoParkingSpotsFoundException, GateNotFoundException {

        Gate gate = gateService.getGateById(gateId);

        if(gate == null){
            throw new GateNotFoundException();
        }

        Vehicle vehicle = vehicleService.insertIfNotExists(vehicleNumber, vehicleType);


        Spot spot;

        try {
            spot = spotAssignmentStrategy.assignSpot(vehicle,gate);
        } catch (Exception e) {
            throw e;
        }


        Date entryTime = new Date();

        Ticket ticket = ticketRepository.createTicket(vehicleNumber,spot,entryTime);
        //System.out.println("Ticket:" + ticket);
        return ticket;
    }

    public Bill generateBill(Ticket ticket){

        Vehicle vehicle = vehicleService.getVehicleByNumber(ticket.getVehicleNumber());
        Date exitTime = new Date();
        /**
         * if today is weekday
         *      this.pricingStrategy = new WeekdayPricingStategy()
         *      pricingStrategy.calculateAmount()
         *  else if today is weekend:
         *      this.pricingStrategy = new WeekendPricingStategy()
         *      pricingStrategy.calculateAmount()
         *
         *  This type of implementation will lead to OCP and SRP violations, hence we are using
         *  factory to get object of pricing strategy
         */
        PricingStrategy pricingStrategy = pricingStrategyFactory.getPricingStrategy(exitTime);
        double amount = pricingStrategy.calculateAmount(vehicle.getVehicleType(), ticket.getEntryTime(), exitTime);
        return billRepository.createBill(ticket.getEntryTime(), exitTime, amount, vehicle);
    }
}
