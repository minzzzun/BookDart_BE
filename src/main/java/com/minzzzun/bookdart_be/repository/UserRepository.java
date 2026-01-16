package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
