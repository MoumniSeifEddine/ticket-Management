package com.camelsoft.ticketmanagement.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotEmpty(message = "First name is required")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    private String lastname;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    private LocalDateTime lastModificationDate;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    private String email;

    private String image;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotNull(message = "Role ID is required")
    private Integer roleId;

    @NotNull(message = "Satisfaction ID is required")
    private Integer satisfactionId;

    private boolean accountLocked;
    private boolean enabled;

}
