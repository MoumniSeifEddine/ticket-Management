package com.camelsoft.ticketmanagement.satisfaction;

import com.camelsoft.ticketmanagement.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatisfactionRepository extends JpaRepository<Satisfaction, Integer> {
    Satisfaction findByName(SatisfactionName name);
}
