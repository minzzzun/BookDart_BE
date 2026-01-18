package com.minzzzun.bookdart_be.mapper;

import com.minzzzun.bookdart_be.dto.PostDto;

import java.util.List;

public interface PostMapper {

    PostDto.DetailResDto detail(Long id);
    List<PostDto.DetailResDto> list(PostDto.ListReqDto param);

    List<PostDto.DetailResDto> pagedList(PostDto.PagedListReqDto param);
    int pagedListCount(PostDto.PagedListReqDto param);
    List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto param);

}
