package com.camelsoft.ticketmanagement.ticket;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketMapper {
    public TicketResponse toTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .expectedResolveDate(ticket.getExpectedResolveDate())
                .contacts(ticket.getContacts())
                .subject(ticket.getSubject())
                .type(ticket.getType())
                .source(ticket.getSource())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .createdDate(ticket.getCreatedDate())
                .lastModifiedDate(ticket.getLastModifiedDate())
                .description(ticket.getDescription())
                .projectId(ticket.getProject().getId())
                .createdBy(ticket.getCreatedBy().getFullName())
                .assignedTo(ticket.getAssignedTo().getFullName())
                .build();
    }

    public List<TicketResponse> toTicketListResponse(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::toTicketResponse)
                .collect(Collectors.toList());
    }
}
