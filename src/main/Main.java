package main;

import main.controller.BillController;
import main.controller.TicketController;
import main.dtos.GenerateBillRequestDTO;
import main.dtos.GenerateTicketRequestDTO;
import main.exceptions.GateNotFoundException;
import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.factories.PricingStrategyFactory;
import main.models.*;
import main.repositories.*;
import main.services.GateService;
import main.services.TicketService;
import main.services.VehicleService;
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

        BasePrice basePrice = new BasePrice(new BaseModel(1), VehicleType.CAR, 20, PriceType.WEEKEND);

        Map<Integer, BasePrice> basePriceMap = new HashMap<Integer, BasePrice>(){{
            put(basePrice.getBaseModel().getId(), basePrice);
        }};
        BasePriceRepository basePriceRepository  =new BasePriceRepository(basePriceMap);

        Slab slab1 = new Slab(new BaseModel(1), VehicleType.CAR, PriceType.WEEKEND, 0, 2, 1.2);
        Slab slab2 = new Slab(new BaseModel(2), VehicleType.CAR, PriceType.WEEKEND, 2, 6, 1.5);
        Slab slab3 = new Slab(new BaseModel(3), VehicleType.CAR, PriceType.WEEKEND, 6, -1, 2);
        Map<Integer, Slab> slabMap = new HashMap<Integer, Slab>(){{
            put(slab1.getBaseModel().getId(), slab1);
            put(slab2.getBaseModel().getId(), slab2);
            put(slab3.getBaseModel().getId(), slab3);
        }};
        SlabRepository slabRepository = new SlabRepository(slabMap);


        GateRepository gateRepository = new GateRepository(gateMap);
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotMap);
        VehicleRepository vechileRepository = new VehicleRepository();


        SpotAssignmentStrategy spotAssignmentStrategy= new NearestSpotAssignmentStrategy(parkingLotRepository);
        GateService gateService = new GateService(gateRepository);
        VehicleService vehicleService = new VehicleService(vechileRepository);

        PricingStrategyFactory pricingStrategyFactory = new PricingStrategyFactory(basePriceRepository,slabRepository);

        TicketService ticketService = new TicketService(spotAssignmentStrategy,gateService,ticketRepository,vehicleService,pricingStrategyFactory,new BillRepository());

        TicketController ticketController = new TicketController(ticketService);
        GenerateTicketRequestDTO requestDTO = new GenerateTicketRequestDTO("123", VehicleType.CAR,1);
        Ticket ticket = ticketController.generateTicket(requestDTO);
        System.out.println(ticket);


        BillController billController = new BillController(ticketService);
        Bill bill = billController.generateBill(new GenerateBillRequestDTO(ticket));
        System.out.println(bill);
    }
}
