package com.camelsoft.ticketmanagement.email;

import lombok.Getter;

@Getter
public enum ResetPasswordEmailTemplateName {
    RESET_PASSWORD_EMAIL("reset_password");

    private final String name;
    ResetPasswordEmailTemplateName(String name) {
        this.name = name;
    }
}
