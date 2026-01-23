package com.minzzzun.bookdart_be.controller;


import com.minzzzun.bookdart_be.dto.CommentDto;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PostDto;
import com.minzzzun.bookdart_be.security.PrincipalDetails;
import com.minzzzun.bookdart_be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentService;

    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null){
            return null;
        }
        return principalDetails.getUser().getId();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody CommentDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(commentService.create(param,getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody CommentDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.update(param,getReqUserId(principalDetails));
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody CommentDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        commentService.delete(param,getReqUserId(principalDetails));
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<CommentDto.DetailResDto> detail(
            DefaultDto.DetailReqDto param,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(commentService.detail(param, getReqUserId(principalDetails)));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto.DetailResDto>> listByPost(
            @PathVariable Long postId) {
        // 삭제되지 않은 댓글만 조회
        CommentDto.ListReqDto param = CommentDto.ListReqDto.builder()
                .postId(postId)
                .deleted(false)
                .build();
        return ResponseEntity.ok(commentService.list(param, null));
    }


}
