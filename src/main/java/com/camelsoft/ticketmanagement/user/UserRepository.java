package com.camelsoft.ticketmanagement.user;

import com.camelsoft.ticketmanagement.role.Role;
import com.camelsoft.ticketmanagement.satisfaction.Satisfaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);
    List<User> findBySatisfaction(Satisfaction Satisfaction);
    List<User> findByRole(Role role);

}
