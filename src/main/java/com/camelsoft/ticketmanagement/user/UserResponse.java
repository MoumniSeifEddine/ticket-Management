package com.camelsoft.ticketmanagement.user;

import com.camelsoft.ticketmanagement.role.Role;
import com.camelsoft.ticketmanagement.satisfaction.Satisfaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;
    private String image;
    private boolean accountLocked;
    private boolean enabled;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private Integer role;
    private Integer satisfaction;

    public String getFullName() {
        return firstname + " " + lastname;
    }
}
