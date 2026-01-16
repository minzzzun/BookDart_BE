package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.Permissiondetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissiondetailRepository extends JpaRepository<Permissiondetail, Long> {
    Permissiondetail findByPermissionIdAndTargetAndFunc(Long permissionId, String target, Integer func);
}
