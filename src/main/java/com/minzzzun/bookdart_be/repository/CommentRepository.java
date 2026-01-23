package com.minzzzun.bookdart_be.repository;

import com.minzzzun.bookdart_be.domain.Comment;
import com.minzzzun.bookdart_be.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdAndDeletedFalse(Long postId);
    Long countByPostIdAndDeletedFalse(Long postId);
    List<Comment> findByUserIdAndDeletedFalse(Long userId);
}
