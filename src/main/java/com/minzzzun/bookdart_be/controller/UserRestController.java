package com.minzzzun.bookdart_be.controller;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.UserDto;
import com.minzzzun.bookdart_be.security.ExternalProperties;
import com.minzzzun.bookdart_be.security.PrincipalDetails;
import com.minzzzun.bookdart_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserRestController {
    final UserService userService;
    final ExternalProperties externalProperties;

    public Long getReqUserId(PrincipalDetails principalDetails){
        if(principalDetails == null || principalDetails.getUser() == null || principalDetails.getUser().getId() == null){
            return null;
        }
        return principalDetails.getUser().getId();
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<DefaultDto.CreateResDto> signup(@RequestBody UserDto.CreateReqDto param){
        return ResponseEntity.ok(userService.signup(param, null));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody UserDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok(userService.create(param, getReqUserId(principalDetails)));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody UserDto.UpdateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        userService.update(params, reqUserId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody UserDto.UpdateReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        userService.delete(params, reqUserId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("")
    public ResponseEntity<UserDto.DetailResDto> detail(DefaultDto.DetailReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(userService.detail(params, reqUserId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<UserDto.DetailResDto>> list(UserDto.ListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(userService.list(params, reqUserId));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(UserDto.PagedListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(userService.pagedList(params, reqUserId));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/scrollList")
    public ResponseEntity<List<UserDto.DetailResDto>> scrollList(UserDto.ScrollListReqDto params, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long reqUserId = getReqUserId(principalDetails);
        return ResponseEntity.ok(userService.scrollList(params, reqUserId));
    }
}
