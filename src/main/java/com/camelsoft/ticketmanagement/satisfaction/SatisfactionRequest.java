package com.camelsoft.ticketmanagement.satisfaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatisfactionRequest {

    @NotNull(message = "Satisfaction name is required")
    private SatisfactionName name;
}
