package com.minzzzun.bookdart_be.controller;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PostDto;
import com.minzzzun.bookdart_be.security.ExternalProperties;
import com.minzzzun.bookdart_be.security.PrincipalDetails;
import com.minzzzun.bookdart_be.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class PostRestController {
    final PostService postService;

    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null){
            return null;
        }
        return principalDetails.getUser().getId();
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PostDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(postService.create(param, getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostDto.UpdateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        postService.update(params, getReqUserId(principalDetails));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostDto.UpdateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        postService.delete(params, getReqUserId(principalDetails));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<PostDto.DetailResDto> detail(DefaultDto.DetailReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(postService.detail(params, getReqUserId(principalDetails)));
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public ResponseEntity<List<PostDto.DetailResDto>> list(PostDto.ListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(postService.list(params, getReqUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PostDto.PagedListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(postService.pagedList(params, getReqUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/scrollList")
    public ResponseEntity<List<PostDto.DetailResDto>> scrollList(PostDto.ScrollListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(postService.scrollList(params, getReqUserId(principalDetails)));
    }
}
