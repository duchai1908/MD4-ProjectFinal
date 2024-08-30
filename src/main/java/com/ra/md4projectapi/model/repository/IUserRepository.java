package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Page<User> findUserByUsernameContainsIgnoreCase(String username, Pageable pageable);
    boolean existsByUsername(String username);
}
