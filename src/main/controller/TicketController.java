package main.controller;

import main.dtos.GenerateTicketRequestDTO;
import main.exceptions.GateNotFoundException;
import main.exceptions.NoParkingSpotsFoundException;
import main.exceptions.ParkingLotDoesnotExist;
import main.models.Ticket;
import main.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Ticket generateTicket(GenerateTicketRequestDTO requestDTO) throws ParkingLotDoesnotExist, NoParkingSpotsFoundException, GateNotFoundException {
        return ticketService.generateTicket(requestDTO.getVehicleNumber(),requestDTO.getVehicleType(),requestDTO.getGateId());
    }
}
