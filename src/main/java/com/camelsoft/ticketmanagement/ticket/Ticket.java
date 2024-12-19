package com.camelsoft.ticketmanagement.ticket;

import com.camelsoft.ticketmanagement.project.Project;
import com.camelsoft.ticketmanagement.user.User;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
@EntityListeners(AuditingEntityListener.class)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private LocalDate expectedResolveDate;
    private String contacts;
    private String subject;
    private String image;
    @Enumerated(EnumType.STRING)
    private TicketType type;
    @Enumerated(EnumType.STRING)
    private TicketSource source ;
    @Enumerated(EnumType.STRING)
    private TicketStatus status ;
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;


    /*@ManyToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    private User agent;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;*/


}
