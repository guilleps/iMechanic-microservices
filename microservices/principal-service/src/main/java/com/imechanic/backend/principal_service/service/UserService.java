package com.imechanic.backend.principal_service.service;

import com.imechanic.backend.principal_service.controller.response.*;
import com.imechanic.backend.principal_service.exception.ResourceNotFoundException;
import com.imechanic.backend.principal_service.model.*;
import com.imechanic.backend.principal_service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with " + email + " email not found"));
    }

    @Transactional
    public void saveUser(UserEntity user) {
        try {
            userRepository.save(user);
            log.info("User saved successfully: {}", user.getEmail());
        } catch (Exception e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Transactional(readOnly = true)
    public String getAttributeUserByEmail(String email, String attribute) {
        return userRepository.findUserAttributeByEmailAndAttribute(email, attribute)
                .orElseThrow(() -> new ResourceNotFoundException("Attribute not found"));
    }

    @Transactional(readOnly = true)
    public UserDTOResponse findUserDTOByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with " + email + " email not found"));

        return new UserDTOResponse(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getRole().getRoleEnum().name());
    }

    public InformationResponseDTO getInformationByUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserEntity userEntity = findUserByEmail(email);

        return new InformationResponseDTO(
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getAddress(),
                userEntity.getPhone()
        );
    }
}
