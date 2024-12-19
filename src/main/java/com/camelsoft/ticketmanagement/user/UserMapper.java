package com.camelsoft.ticketmanagement.user;

import com.camelsoft.ticketmanagement.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .image(user.getImage())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .accountLocked(user.isAccountLocked())
                .enabled(user.isEnabled())
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(user.getLastModifiedDate())
                .role(user.getRole().getId())
                .satisfaction(user.getSatisfaction() != null ? user.getSatisfaction().getId() : null)
                .build();
    }

    public List<UserResponse> toUserListResponse(List<User> users) {
        return users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }
}
