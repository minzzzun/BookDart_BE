package com.minzzzun.bookdart_be.mapper;

import com.minzzzun.bookdart_be.dto.CommentDto;
import com.minzzzun.bookdart_be.dto.PostDto;

import java.util.List;

public interface CommentMapper {
    CommentDto.DetailResDto detail(Long id);
    List<CommentDto.DetailResDto> list(CommentDto.ListReqDto param);
    List<CommentDto.DetailResDto> pagedList(CommentDto.PagedListReqDto param);
    int pagedListCount(CommentDto.PagedListReqDto param);
    List<CommentDto.DetailResDto> scrollList(CommentDto.ScrollListReqDto param);
}
