package com.imechanic.backend.order_service.config.service;

import com.imechanic.backend.order_service.client.UserClient;
import com.imechanic.backend.order_service.controller.response.UserDTOResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUserDetailService implements UserDetailsService {
    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTOResponse userDTO = userClient.getUserByEmail(username);
        return new User(userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.isEnabled(),
                userDTO.isAccountNonExpired(),
                userDTO.isCredentialsNonExpired(),
                userDTO.isAccountNonLocked(),
                AuthorityUtils.createAuthorityList(userDTO.getRole()));
    }
}