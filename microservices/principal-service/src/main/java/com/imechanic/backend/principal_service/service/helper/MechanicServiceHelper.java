package com.imechanic.backend.principal_service.service.helper;

import com.imechanic.backend.principal_service.model.Mechanic;
import com.imechanic.backend.principal_service.repository.MechanicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MechanicServiceHelper {
    private final MechanicRepository mechanicRepository;

    public Mechanic findMechanicByEmail(String email) {
        return mechanicRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Mechanic with email: " + email + " not found"));
    }
}