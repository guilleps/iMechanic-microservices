package com.imechanic.backend.principal_service.config.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.imechanic.backend.principal_service.config.util.JwtUtils;
import com.imechanic.backend.principal_service.controller.request.AuthenticationLoginDTORequest;
import com.imechanic.backend.principal_service.controller.request.AuthenticationSignUpDTORequest;
import com.imechanic.backend.principal_service.controller.response.LoginDTOResponse;
import com.imechanic.backend.principal_service.controller.response.MessageResponse;
import com.imechanic.backend.principal_service.controller.response.SignUpDTOResponse;
import com.imechanic.backend.principal_service.exception.IncorrectCredentials;
import com.imechanic.backend.principal_service.exception.TokenNotFound;
import com.imechanic.backend.principal_service.model.Mechanic;
import com.imechanic.backend.principal_service.model.UserEntity;
import com.imechanic.backend.principal_service.repository.MechanicRepository;
import com.imechanic.backend.principal_service.repository.UserRepository;
import com.imechanic.backend.principal_service.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MechanicRepository mechanicRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JavaMailSender javaMailSender;

    @Value("${spring.email.sender.user}")
    private String mailOrigin;

    @Value("${url.client.side}")
    private String baseUrl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty()) {
            Optional<Mechanic> mechanicOptional = mechanicRepository.findByEmail(username);
            return mechanicOptional.map(mechanic -> new User(mechanic.getEmail(), mechanic.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(mechanic.getRole().getRoleEnum().name())))
                    .orElseThrow(() -> new UsernameNotFoundException("Mechanic with email " + username + " not found"));
        } else {
            return userOptional.map(user -> new User(user.getEmail(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), AuthorityUtils.createAuthorityList(user.getRole().getRoleEnum().name())))
                    .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
        }
    }

    public LoginDTOResponse loginUser(AuthenticationLoginDTORequest loginDTORequest) {
        String email = loginDTORequest.getEmail();
        String password = loginDTORequest.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = loadUserByUsername(email);
        if (!userDetails.isEnabled()) {
            throw new IncorrectCredentials("The account is not enabled");
        }

        String accessToken = jwtUtils.createToken(authentication);

        return new LoginDTOResponse("User logged successfully", accessToken,
                authentication.getAuthorities().stream()
                        .findFirst()
                        .map(GrantedAuthority::getAuthority)
                        .orElse(""));
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new IncorrectCredentials("Invalid email or password");
        }

        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public SignUpDTOResponse createUser(AuthenticationSignUpDTORequest signUpDTORequest) {

        var user = UserEntity.builder()
                .email(signUpDTORequest.getEmail())
                .password(passwordEncoder.encode(signUpDTORequest.getPassword()))
                .name(signUpDTORequest.getName())
                .phone(signUpDTORequest.getPhone())
                .address(signUpDTORequest.getAddress())
                .role(roleService.findByRoleEnum(signUpDTORequest.getRole()))
                .isEnabled(false)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(user);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), AuthorityUtils.createAuthorityList(userCreated.getRole().getRoleEnum().name()));
        String accessToken = jwtUtils.createToken(authentication);

        sendSimpleMessage(
                userCreated.getEmail(),
                "Account Verification - iMechanic",
                "Estimado/a " + user.getName() + ",\n\nGracias por registrarte en iMechanic. Por favor, haz clic en el siguiente enlace para confirmar tu cuenta:\n\n" + baseUrl + "/verificar/" + accessToken + "\n\nSaludos,\nEl equipo de iMechanic");

        return new SignUpDTOResponse("Welcome to iMechanic, '" + signUpDTORequest.getName() + "' .You have been successfully registered.");
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailOrigin);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public MessageResponse confirmAccount(String token) {
        DecodedJWT decodedJWT = jwtUtils.validateToken(token);
        String username = jwtUtils.extractUsername(decodedJWT);

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new TokenNotFound("The user token " + username + " is invalid invalid."));

        user.setEnabled(true);
        userRepository.save(user);
        return new MessageResponse("Account successfully confirmed for " + username);
    }

}