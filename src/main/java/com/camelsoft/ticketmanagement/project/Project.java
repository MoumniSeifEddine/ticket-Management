package com.camelsoft.ticketmanagement.project;

import com.camelsoft.ticketmanagement.ticket.Ticket;
import com.camelsoft.ticketmanagement.user.User;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String projectName;
    private String domainName;
    private String projectLanguage;
    private String projectDetails;
    private String image;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @OneToMany(mappedBy = "project")
    private List<Ticket> tickets;

    /*@ManyToOne
    @JoinColumn(name = "client_contact_id", referencedColumnName = "email")
    private User clientContact;

    @ManyToOne
    @JoinColumn(name = "agent_id", referencedColumnName = "id")
    private User agent;*/
}
