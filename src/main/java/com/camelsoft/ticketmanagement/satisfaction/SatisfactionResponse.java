package com.camelsoft.ticketmanagement.satisfaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SatisfactionResponse {
    private Integer id;
    private SatisfactionName name;
}