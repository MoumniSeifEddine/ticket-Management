package com.camelsoft.ticketmanagement.ticket;

import com.camelsoft.ticketmanagement.project.Project;
import com.camelsoft.ticketmanagement.project.ProjectRepository;
import com.camelsoft.ticketmanagement.user.User;
import com.camelsoft.ticketmanagement.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
    }
    public List<Ticket> getTicketsByStatue(String statue) {
        return ticketRepository.findByStatus(TicketStatus.valueOf(statue));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Transactional
    public List<Ticket> getTicketsByProjectId(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return ticketRepository.findByProject(project);
    }

    public void createTicket(Ticket ticket) {
        ticket.setCreatedDate(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public void updateTicket(Integer id, Ticket updatedTicket) {
        ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setTitle(updatedTicket.getTitle());
                    ticket.setExpectedResolveDate(updatedTicket.getExpectedResolveDate());
                    ticket.setContacts(updatedTicket.getContacts());
                    ticket.setSubject(updatedTicket.getSubject());
                    ticket.setType(updatedTicket.getType());
                    ticket.setSource(updatedTicket.getSource());
                    ticket.setStatus(updatedTicket.getStatus());
                    ticket.setPriority(updatedTicket.getPriority());
                    ticket.setDescription(updatedTicket.getDescription());
                    ticket.setImage(updatedTicket.getImage());
                    ticket.setProject(updatedTicket.getProject());
                    ticket.setAssignedTo(updatedTicket.getAssignedTo());
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
    }

    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }

    public Project getProjectById(Integer projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public List<Ticket> getTicketByCreator(Integer id) {
        User user = userService.getUserById(id);
        return ticketRepository.findByCreatedBy(user);
    }
    public List<Ticket> getTicketByAssignedTo(Integer id) {
        User user = userService.getUserById(id);
        return ticketRepository.findByAssignedTo(user);
    }
}
