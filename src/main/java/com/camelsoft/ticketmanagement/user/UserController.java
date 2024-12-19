package com.camelsoft.ticketmanagement.user;

import com.camelsoft.ticketmanagement.role.Role;
import com.camelsoft.ticketmanagement.role.RoleRepository;
import com.camelsoft.ticketmanagement.satisfaction.Satisfaction;
import com.camelsoft.ticketmanagement.satisfaction.SatisfactionName;
import com.camelsoft.ticketmanagement.satisfaction.SatisfactionRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.valueOf;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final SatisfactionRepository satisfactionRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/All")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> getAllUsers() {
        return userMapper.toUserListResponse(userService.getAllUsers());
    }
    @GetMapping("/ByRole")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> getAllUsersByRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        return userMapper.toUserListResponse(userService.getUserByRole(role));
    }
    @GetMapping("/ByEmail")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse getClientByEmail(String email) {
        return userMapper.toUserResponse(userService.getUserByEmail(email));
    }

    @GetMapping("/BySatisfaction")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> getAllUsersBySatisfaction(SatisfactionName Satisfaction) {
        Satisfaction satisfaction = satisfactionRepository.findByName(Satisfaction);
        return userMapper.toUserListResponse(userService.getUserBySatisfaction(satisfaction));
    }

    @GetMapping("/ById")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse getClientById(Integer id) {
        return userMapper.toUserResponse(userService.getUserById(id));
    }

    @PutMapping("/update/{id}")
    public  String updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {

        User updatedUser = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .lastModifiedDate(userRequest.getLastModificationDate())
                .build();
        userService.updateUser(id, updatedUser);
        return "Project updated successfully";
    }
    //@Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Project deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal error, please contact the admin");
        }
    }
}
