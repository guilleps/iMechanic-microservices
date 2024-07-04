package com.imechanic.backend.principal_service.controller;

import com.imechanic.backend.principal_service.config.service.IUserDetailService;
import com.imechanic.backend.principal_service.controller.request.AuthenticationLoginDTORequest;
import com.imechanic.backend.principal_service.controller.request.AuthenticationSignUpDTORequest;
import com.imechanic.backend.principal_service.controller.response.LoginDTOResponse;
import com.imechanic.backend.principal_service.controller.response.MessageResponse;
import com.imechanic.backend.principal_service.controller.response.SignUpDTOResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserDetailService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpDTOResponse> signUp(@Valid @RequestBody AuthenticationSignUpDTORequest signUpDTORequest) {
        return new ResponseEntity<>(userService.createUser(signUpDTORequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTOResponse> login(@Valid @RequestBody AuthenticationLoginDTORequest loginDTORequest) {
        return new ResponseEntity<>(userService.loginUser(loginDTORequest), HttpStatus.OK);
    }

    @GetMapping("/confirmation/{token}")
    public MessageResponse confirmAccount(@PathVariable String token) {
        return userService.confirmAccount(token);
    }
}