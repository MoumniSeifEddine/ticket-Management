package com.camelsoft.ticketmanagement.ticket;

import com.camelsoft.ticketmanagement.user.User;
import com.camelsoft.ticketmanagement.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final TicketMapper ticketMapper;

    @GetMapping("/all-tickets")
    public List<TicketResponse> getAllTickets() {
        return ticketMapper.toTicketListResponse(ticketService.getAllTickets());
    }

    //@Secured("ROLE_ADMIN")
    @GetMapping("/ticket/{id}")
    public TicketResponse getTicketById(@PathVariable("id") Integer id) {
        return ticketMapper.toTicketResponse(ticketService.getTicketById(id));
    }

    //@Secured("ROLE_ADMIN")
    @GetMapping("/project-tickets/{projectId}")
    public List<TicketResponse> getTicketsByProject(@PathVariable("projectId") Integer projectId) {
        return ticketMapper.toTicketListResponse(ticketService.getTicketsByProjectId(projectId));
    }

    //@Secured("ROLE_ADMIN")
    @GetMapping("/ticket-statue/{statue}")
    public List<TicketResponse> getTicketsByStatue(@PathVariable("statue") String statue) {
        return ticketMapper.toTicketListResponse(ticketService.getTicketsByStatue(statue));
    }

    //@Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public String createTicket(@RequestBody TicketRequest ticketRequest) {
        User assignedTo = userService.getUserById(ticketRequest.getAssignedTo());
        User createdBy = userService.getUserById(ticketRequest.getCreatedBy());
        Ticket ticket = Ticket.builder()
                .title(ticketRequest.getTitle())
                .expectedResolveDate(ticketRequest.getExpectedResolveDate())
                .contacts(ticketRequest.getContacts())
                .subject(ticketRequest.getSubject())
                .type(ticketRequest.getType())
                .source(ticketRequest.getSource())
                .status(ticketRequest.getStatus())
                .priority(ticketRequest.getPriority())
                .description(ticketRequest.getDescription())
                .image(ticketRequest.getImage())
                .project(ticketService.getProjectById(ticketRequest.getProjectId()))
                .createdBy(createdBy)
                .assignedTo(assignedTo)
                .build();

        ticketService.createTicket(ticket);
        return "Ticket created successfully";
    }

    //@Secured("ROLE_ADMIN")
    @PutMapping("/update/{id}")
    public String updateTicket(@PathVariable Integer id, @RequestBody TicketRequest ticketRequest) {
        User assignedTo = userService.getUserById(ticketRequest.getAssignedTo());

        Ticket updatedTicket = Ticket.builder()
                .title(ticketRequest.getTitle())
                .expectedResolveDate(ticketRequest.getExpectedResolveDate())
                .contacts(ticketRequest.getContacts())
                .subject(ticketRequest.getSubject())
                .type(ticketRequest.getType())
                .source(ticketRequest.getSource())
                .status(ticketRequest.getStatus())
                .priority(ticketRequest.getPriority())
                .description(ticketRequest.getDescription())
                .image(ticketRequest.getImage())
                .project(ticketService.getProjectById(ticketRequest.getProjectId()))
                .assignedTo(assignedTo)
                .build();
        ticketService.updateTicket(id, updatedTicket);
        return "Ticket updated successfully";
    }

    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Integer id) {
        try {
            ticketService.deleteTicketById(id);
            return ResponseEntity.ok("Ticket deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error, please contact the admin");
        }
    }

    @GetMapping("/creator-tickets/{id}")
    public List<TicketResponse> getOwnerTickets(@PathVariable Integer id) {
        return ticketMapper.toTicketListResponse(ticketService.getTicketByCreator(id));
    }

    @GetMapping("/assignedTo-tickets/{id}")
    public List<TicketResponse> getAssignedToTickets(@PathVariable Integer id) {
        return ticketMapper.toTicketListResponse(ticketService.getTicketByAssignedTo(id));
    }

}
