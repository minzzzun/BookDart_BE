package com.minzzzun.bookdart_be.service;

import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    DefaultDto.CreateResDto create(CommentDto.CreateReqDto param, Long reqUserId);
    void update(CommentDto.UpdateReqDto param, Long reqUserId);
    void delete(CommentDto.UpdateReqDto param, Long reqUserId);
    CommentDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<CommentDto.DetailResDto> list(CommentDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(CommentDto.PagedListReqDto param, Long reqUserId);
    List<CommentDto.DetailResDto> scrollList(CommentDto.ScrollListReqDto param, Long reqUserId);
}
