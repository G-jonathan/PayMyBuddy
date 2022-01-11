package com.openclassroomsproject.paymybuddy.backend.repository;

import com.openclassroomsproject.paymybuddy.backend.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
}
