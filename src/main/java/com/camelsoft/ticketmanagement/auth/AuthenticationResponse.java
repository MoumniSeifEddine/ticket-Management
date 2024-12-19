package com.camelsoft.ticketmanagement.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private Integer id;
    private String token;
    private String image;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
