package com.minzzzun.bookdart_be.controller;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.NoticeDto;
import com.minzzzun.bookdart_be.security.PrincipalDetails;
import com.minzzzun.bookdart_be.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {
    final NoticeService noticeService;

    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null){
            return null;
        }
        return principalDetails.getUser().getId();
    }

    // ========== 관리자 전용 API ==========

    @PreAuthorize("hasRole('USER')")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody NoticeDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(noticeService.create(param, getReqUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody NoticeDto.UpdateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        noticeService.update(params, getReqUserId(principalDetails));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody NoticeDto.UpdateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        noticeService.delete(params, getReqUserId(principalDetails));
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // ========== 일반 유저 API (조회만 가능) ==========

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<NoticeDto.DetailResDto> detail(DefaultDto.DetailReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(noticeService.detail(params, getReqUserId(principalDetails)));
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public ResponseEntity<List<NoticeDto.DetailResDto>> list(NoticeDto.ListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(noticeService.list(params, getReqUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(NoticeDto.PagedListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(noticeService.pagedList(params, getReqUserId(principalDetails)));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/scrollList")
    public ResponseEntity<List<NoticeDto.DetailResDto>> scrollList(NoticeDto.ScrollListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(noticeService.scrollList(params, getReqUserId(principalDetails)));
    }
}
