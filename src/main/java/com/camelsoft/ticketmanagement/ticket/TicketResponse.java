package com.camelsoft.ticketmanagement.ticket;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

    private Integer id;
    private String title;
    private LocalDate expectedResolveDate;
    private String contacts;
    private String subject;
    private TicketType type;
    private TicketSource source;
    private TicketStatus status;
    private TicketPriority priority;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String description;
    private String image;
    private Integer projectId;
    private String createdBy;
    private String assignedTo;
}
