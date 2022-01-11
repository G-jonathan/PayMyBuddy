package com.openclassroomsproject.paymybuddy.backend.service.impl;

import com.openclassroomsproject.paymybuddy.backend.model.Authorities;
import com.openclassroomsproject.paymybuddy.backend.repository.AuthoritiesRepository;
import com.openclassroomsproject.paymybuddy.backend.service.IAuthoritiesService;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements IAuthoritiesService {
    private final AuthoritiesRepository authoritiesRepository;

    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public void createRoleUser(String email) {
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_USER");
        authorities.setEmail(email);
        authoritiesRepository.save(authorities);
    }
}