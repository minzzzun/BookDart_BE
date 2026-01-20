package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // 특정 포스트에 사용자가 좋아요를 했는지 확인
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    // 특정 포스트의 좋아요 목록
    List<Like> findByPostIdAndDeletedFalse(Long postId);

    // 특정 포스트의 좋아요 수
    Long countByPostIdAndDeletedFalse(Long postId);

    // 특정 사용자의 좋아요 목록
    List<Like> findByUserIdAndDeletedFalse(Long userId);

    // 좋아요 존재 여부 확인
    boolean existsByPostIdAndUserIdAndDeletedFalse(Long postId, Long userId);
}
