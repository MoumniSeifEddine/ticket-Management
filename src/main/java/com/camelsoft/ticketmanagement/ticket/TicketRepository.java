package com.camelsoft.ticketmanagement.ticket;

import com.camelsoft.ticketmanagement.project.Project;
import com.camelsoft.ticketmanagement.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByCreatedBy(User createdBy);
    List<Ticket> findByProject(Project project);
    List<Ticket> findByAssignedTo(User assignedTo);
    List<Ticket> findByStatus(TicketStatus status);
}
