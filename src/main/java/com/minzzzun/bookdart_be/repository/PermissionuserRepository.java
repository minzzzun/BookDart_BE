package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.Permissionuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionuserRepository extends JpaRepository<Permissionuser, Long> {
    Permissionuser findByPermissionIdAndUserId(Long permissionId, Long userId);
}
