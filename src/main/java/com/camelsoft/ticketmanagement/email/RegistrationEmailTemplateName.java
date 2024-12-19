package com.camelsoft.ticketmanagement.email;

import lombok.Getter;

@Getter
public enum RegistrationEmailTemplateName {
    ACTIVATE_ACCOUNT_EMAIL("activate_account");

    private final String name;
    RegistrationEmailTemplateName(String name) {
        this.name = name;
    }
}
