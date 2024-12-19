package com.camelsoft.ticketmanagement.user;

import com.camelsoft.ticketmanagement.project.Project;
import com.camelsoft.ticketmanagement.role.Role;
import com.camelsoft.ticketmanagement.role.RoleRepository;
import com.camelsoft.ticketmanagement.satisfaction.Satisfaction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<User> getUserBySatisfaction(Satisfaction satisfaction) {
        return userRepository.findBySatisfaction(satisfaction);
    }
    public List<User> getUserByRole(Role role) {
        return userRepository.findByRole(role);
    }
    public User updateUser(Integer id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                    user.setEmail(updatedUser.getEmail());
                    user.setImage(updatedUser.getImage());
                    user.setLastModifiedDate(LocalDateTime.now());
                    user.setFirstname(updatedUser.getFirstname());
                    user.setLastname(updatedUser.getLastname());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}