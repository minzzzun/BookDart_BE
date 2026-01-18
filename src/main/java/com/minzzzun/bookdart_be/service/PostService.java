package com.minzzzun.bookdart_be.service;

import com.minzzzun.bookdart_be.dto.DefaultDto;

import com.minzzzun.bookdart_be.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    DefaultDto.CreateResDto create(PostDto.CreateReqDto param, Long reqPostId);
    void update(PostDto.UpdateReqDto param, Long reqPostId);
    void delete(PostDto.UpdateReqDto param, Long reqPostId);
    PostDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqPostId);
    List<PostDto.DetailResDto> list(PostDto.ListReqDto param, Long reqPostId);
    DefaultDto.PagedListResDto pagedList(PostDto.PagedListReqDto param, Long reqPostId);
    List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto param, Long reqPostId);
}
