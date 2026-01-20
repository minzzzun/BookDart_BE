package com.minzzzun.bookdart_be.service;


import com.minzzzun.bookdart_be.domain.Like;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.LikeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService {

    // 좋아요 토글 (좋아요/취소)
    LikeDto.ToggleResDto toggle(LikeDto.ToggleReqDto param, Long reqUserId);

    // 좋아요 상세 조회
    LikeDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);

    // 특정 포스트의 좋아요 목록
    List<LikeDto.DetailResDto> listByPost(LikeDto.ListByPostReqDto param, Long reqUserId);

    // 특정 포스트의 좋아요 수
    Long countByPost(Long postId);

    // 사용자가 특정 포스트에 좋아요 했는지 확인
    Boolean isLiked(Long postId, Long userId);

    // 페이징 목록
    DefaultDto.PagedListResDto pagedList(LikeDto.PagedListReqDto param, Long reqUserId);
}
