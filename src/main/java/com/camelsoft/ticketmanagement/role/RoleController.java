package com.camelsoft.ticketmanagement.role;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponse> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(@RequestBody RoleRequest roleRequest) {
        return roleService.createRole(roleRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponse updateRole(@PathVariable Integer id, @RequestBody RoleRequest roleRequest) {
        return roleService.updateRole(id, roleRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
    }
}
