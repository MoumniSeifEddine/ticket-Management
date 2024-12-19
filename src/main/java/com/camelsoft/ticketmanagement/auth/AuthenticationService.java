package com.camelsoft.ticketmanagement.auth;

import com.camelsoft.ticketmanagement.email.RegistrationEmailService;
import com.camelsoft.ticketmanagement.email.RegistrationEmailTemplateName;
import com.camelsoft.ticketmanagement.email.ResetPasswordEmailService;
import com.camelsoft.ticketmanagement.email.ResetPasswordEmailTemplateName;
import com.camelsoft.ticketmanagement.role.RoleRepository;
import com.camelsoft.ticketmanagement.security.JwtService;
import com.camelsoft.ticketmanagement.security.UserDetailsServiceImpl;
import com.camelsoft.ticketmanagement.user.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final RegistrationEmailService registrationEmailService;
    private final TokenRepository tokenRepository;
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~";
    private static final SecureRandom random = new SecureRandom();
    private final ResetPasswordEmailService resetPasswordEmailService;


    public void register(RegistrationRequest request) throws MessagingException {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .image(request.getImage())
                .email(request.getEmail())
                .createdDate(LocalDateTime.now())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .role(roleRepository.findByName(request.getRole()))
                .build();
        userRepository.save(user);
        sendValidationEmail(user,request.getPassword());
    }

    private Integer generateId() {
        int id;
        do {
            id = 1000 + random.nextInt(9000);
        } while (userRepository.existsById(id));
        return id;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwtToken)
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .role(user.getRole().getName())
                .build();
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                // todo exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser(),null);
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    private String generateAndSaveActivationToken(User user) {
        // Generate a token
        String generatedToken = generateActivationCode(50);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(User user, String rawPassword) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        String confirmationUrl = "http://localhost:8088/api/v1/auth/activate-account?token="+newToken;
        registrationEmailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                rawPassword,
                RegistrationEmailTemplateName.ACTIVATE_ACCOUNT_EMAIL,
                confirmationUrl,
                "Account activation"
                );
    }

    private String generateActivationCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return "\"" + sb.toString() + "\"";
    }

    public void resetPassword(ResetPasswordRequest request) throws MessagingException {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("User with email " + request.getEmail() + " not found"));

        String newPassword = generatePassword(10);
        user.setPassword(passwordEncoder.encode(newPassword));
        sendResetPasswordEmail(user, newPassword);
        userRepository.save(user);
    }

    private void sendResetPasswordEmail(User user, String generatedPassword) throws MessagingException {
        resetPasswordEmailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                generatedPassword,
                ResetPasswordEmailTemplateName.RESET_PASSWORD_EMAIL,
                "Reset password"
        );
    }

    private String generatePassword(int length){
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

}
