package com.camelsoft.ticketmanagement.satisfaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SatisfactionService {

    private final SatisfactionRepository satisfactionRepository;

    public Satisfaction createSatisfaction(Satisfaction satisfaction) {
        return satisfactionRepository.save(satisfaction);
    }

    public Satisfaction getSatisfactionById(Integer id) {
        return satisfactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Satisfaction not found"));
    }

    public List<Satisfaction> getAllSatisfactions() {
        return satisfactionRepository.findAll();
    }

    public Satisfaction updateSatisfaction(Integer id, Satisfaction satisfactionDetails) {
        Satisfaction satisfaction = getSatisfactionById(id);
        satisfaction.setName(satisfactionDetails.getName());
        return satisfactionRepository.save(satisfaction);
    }

    public void deleteSatisfaction(Integer id) {
        satisfactionRepository.deleteById(id);
    }
}
