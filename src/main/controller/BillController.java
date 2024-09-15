package main.controller;

import main.dtos.GenerateBillRequestDTO;
import main.models.Bill;
import main.models.Ticket;
import main.services.TicketService;

public class BillController {
    private TicketService ticketService;

    public BillController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public Bill generateBill(GenerateBillRequestDTO generateBillRequestDTO){
        return ticketService.generateBill(generateBillRequestDTO.getTicket());
    }
}
