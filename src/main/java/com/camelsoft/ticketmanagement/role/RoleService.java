package com.camelsoft.ticketmanagement.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toRoleListResponse(roles);
    }

    @Transactional(readOnly = true)
    public RoleResponse getRoleById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return roleMapper.toRoleResponse(role);
    }

    @Transactional
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRoleEntity(roleRequest);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toRoleResponse(savedRole);
    }

    @Transactional
    public RoleResponse updateRole(Integer id, RoleRequest roleRequest) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        existingRole.setName(roleRequest.getName());

        Role updatedRole = roleRepository.save(existingRole);
        return roleMapper.toRoleResponse(updatedRole);
    }

    @Transactional
    public void deleteRole(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
