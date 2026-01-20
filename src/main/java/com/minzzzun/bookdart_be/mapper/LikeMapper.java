package com.minzzzun.bookdart_be.mapper;

import com.minzzzun.bookdart_be.dto.LikeDto;

import java.util.List;

public interface LikeMapper {

    LikeDto.DetailResDto detail(Long id);
    List<LikeDto.DetailResDto> listByPost(LikeDto.ListByPostReqDto param);
    Long countByPost(Long postId);
    List<LikeDto.DetailResDto> pagedList(LikeDto.PagedListReqDto param);
    int pagedListCount(LikeDto.PagedListReqDto param);
}
