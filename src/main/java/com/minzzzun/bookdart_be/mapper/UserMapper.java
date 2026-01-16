package com.minzzzun.bookdart_be.mapper;

import com.minzzzun.bookdart_be.dto.UserDto;

import java.util.List;

public interface UserMapper {

    UserDto.DetailResDto detail(Long id);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto param);

    List<UserDto.DetailResDto> pagedList(UserDto.PagedListReqDto param);
    int pagedListCount(UserDto.PagedListReqDto param);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param);

}
