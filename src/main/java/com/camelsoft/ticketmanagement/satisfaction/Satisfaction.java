package com.camelsoft.ticketmanagement.satisfaction;

import com.camelsoft.ticketmanagement.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "satisfaction")
public class Satisfaction {
    @Id
    @GeneratedValue
    private Integer id;
    private SatisfactionName name;
    @OneToMany(mappedBy = "satisfaction")
    @JsonIgnore
    private List<User> users;
}
