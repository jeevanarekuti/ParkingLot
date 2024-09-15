package main.dtos;

import main.models.Ticket;

public class GenerateBillRequestDTO {
    private Ticket ticket;

    public GenerateBillRequestDTO(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
