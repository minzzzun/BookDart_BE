package com.minzzzun.bookdart_be.controller;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.LikeDto;
import com.minzzzun.bookdart_be.security.PrincipalDetails;
import com.minzzzun.bookdart_be.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/like")
@RestController
public class LikeRestController {

    private final LikeService likeService;


    private Long getReqUserId(PrincipalDetails principalDetails) {
        if (principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null) {
            return null;
        }
        return principalDetails.getUser().getId();
    }


    // 좋아요 토글 (좋아요/취소)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/toggle")
    public ResponseEntity<LikeDto.ToggleResDto> toggle(
            @RequestBody LikeDto.ToggleReqDto param,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(likeService.toggle(param, getReqUserId(principalDetails)));
    }

    // 좋아요 상세 조회
    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<LikeDto.DetailResDto> detail(
            DefaultDto.DetailReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(likeService.detail(params, getReqUserId(principalDetails)));
    }

    // 특정 포스트의 좋아요 목록
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<LikeDto.DetailResDto>> listByPost(
            @PathVariable Long postId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        LikeDto.ListByPostReqDto param = LikeDto.ListByPostReqDto.builder().postId(postId).build();
        return ResponseEntity.ok(likeService.listByPost(param, getReqUserId(principalDetails)));
    }

    // 특정 포스트의 좋아요 수
    @GetMapping("/count/{postId}")
    public ResponseEntity<Long> countByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(likeService.countByPost(postId));
    }

    // 사용자가 특정 포스트에 좋아요 했는지 확인
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/check/{postId}")
    public ResponseEntity<Boolean> isLiked(
            @PathVariable Long postId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(likeService.isLiked(postId, getReqUserId(principalDetails)));
    }

    // 페이징 목록
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(
            LikeDto.PagedListReqDto params,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(likeService.pagedList(params, getReqUserId(principalDetails)));
    }


}
