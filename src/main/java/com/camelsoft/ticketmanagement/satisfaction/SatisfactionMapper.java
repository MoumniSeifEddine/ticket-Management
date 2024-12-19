package com.camelsoft.ticketmanagement.satisfaction;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SatisfactionMapper {

    public SatisfactionResponse toSatisfactionResponse(Satisfaction satisfaction) {
        return SatisfactionResponse.builder()
                .id(satisfaction.getId())
                .name(satisfaction.getName())
                .build();
    }

    public List<SatisfactionResponse> toSatisfactionListResponse(List<Satisfaction> satisfactions) {
        return satisfactions.stream()
                .map(this::toSatisfactionResponse)
                .collect(Collectors.toList());
    }

    public Satisfaction toSatisfactionEntity(SatisfactionRequest request) {
        return Satisfaction.builder()
                .name(request.getName())
                .build();
    }
}
