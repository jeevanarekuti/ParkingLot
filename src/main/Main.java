package main;

import main.controller.TicketController;
import main.dtos.GenerateTicketRequestDTO;
import main.exceptions.GateNotFoundException;
import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.models.*;
import main.repositories.GateRepository;
import main.repositories.ParkingLotRepository;
import main.repositories.TicketRepository;
import main.services.GateService;
import main.services.TicketService;
import main.strategy.spotAssignment.NearestSpotAssignmentStrategy;
import main.strategy.spotAssignment.SpotAssignmentStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ParkingLotDoesnotExist, NoParkingSpotsFoundException, GateNotFoundException {

        Spot spot1 = new Spot(new BaseModel(1), "A1", VehicleType.CAR);
        Spot spot2 = new Spot(new BaseModel(2), "A2", VehicleType.CAR);
        Spot spot3 = new Spot(new BaseModel(3), "A3", VehicleType.CAR);
        Spot spot4 = new Spot(new BaseModel(4), "A4", VehicleType.CAR);

        Floor floor1 = new Floor(new BaseModel(1), Arrays.asList(spot1, spot2), 1);
        Floor floor2 = new Floor(new BaseModel(2), Arrays.asList(spot3, spot4), 2);

        Gate gate1 = new Gate(new BaseModel(1),"G1");
        Gate gate2 = new Gate(new BaseModel(2),"G2");

        Map<Integer, Gate> gateMap = new HashMap<Integer, Gate>(){{
            put(gate1.getBaseModel().getId(), gate1);
            put(gate2.getBaseModel().getId(), gate2);
        }};

        ParkingLot parkingLot = new ParkingLot(new BaseModel(1),
                Arrays.asList(floor1,floor2), Arrays.asList(gate1, gate2));
        Map<Integer, ParkingLot> parkingLotMap = new HashMap<Integer, ParkingLot>(){{
            put(parkingLot.getBaseModel().getId(), parkingLot);
        }};




        GateRepository gateRepository = new GateRepository(gateMap);
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);

        SpotAssignmentStrategy spotAssignmentStrategy= new NearestSpotAssignmentStrategy(parkingLotRepository);

        GateService gateService = new GateService(gateRepository);

        TicketService ticketService = new TicketService(spotAssignmentStrategy,gateService,ticketRepository);
        TicketController ticketController = new TicketController(ticketService);

        GenerateTicketRequestDTO requestDTO = new GenerateTicketRequestDTO("123", VehicleType.CAR,1);
        Ticket ticket = ticketController.generateTicket(requestDTO);
        System.out.println(ticket);
    }
}
