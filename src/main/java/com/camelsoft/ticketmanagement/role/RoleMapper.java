package com.camelsoft.ticketmanagement.role;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleMapper {

    public RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public List<RoleResponse> toRoleListResponse(List<Role> roles) {
        return roles.stream()
                .map(this::toRoleResponse)
                .collect(Collectors.toList());
    }

    public Role toRoleEntity(RoleRequest request) {
        return Role.builder()
                .name(request.getName())
                .build();
    }
}
