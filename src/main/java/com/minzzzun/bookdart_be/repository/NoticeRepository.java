package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByAdminId(Long adminId);
    List<Notice> findByPinnedTrue();
}
