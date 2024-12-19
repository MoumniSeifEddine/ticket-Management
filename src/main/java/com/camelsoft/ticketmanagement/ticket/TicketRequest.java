package com.camelsoft.ticketmanagement.ticket;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TicketRequest {
    @NotEmpty
    private String title;

    private LocalDate expectedResolveDate;

    @NotEmpty
    private String contacts;

    @NotEmpty
    private String subject;

    @NotNull
    private TicketType type;

    @NotNull
    private TicketSource source;

    @NotNull
    private TicketStatus status;

    @NotNull
    private TicketPriority priority;

    @NotNull
    private Integer createdBy;

    @NotNull
    private Integer assignedTo;

    @NotNull
    private Integer projectId;

    @NotEmpty
    private String description;

    private String image;
}
