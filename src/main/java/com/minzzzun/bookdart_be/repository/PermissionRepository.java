package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
