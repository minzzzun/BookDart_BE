package com.minzzzun.bookdart_be.service;

import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.UserDto;

import java.util.List;

public interface UserService {
    DefaultDto.CreateResDto signup(UserDto.CreateReqDto param, Long reqUserId);

    DefaultDto.CreateResDto create(UserDto.CreateReqDto param, Long reqUserId);
    void update(UserDto.UpdateReqDto param, Long reqUserId);
    void delete(UserDto.UpdateReqDto param, Long reqUserId);
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param, Long reqUserId);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param, Long reqUserId);
}
