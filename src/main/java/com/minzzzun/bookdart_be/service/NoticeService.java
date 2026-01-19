package com.minzzzun.bookdart_be.service;

import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {
    // 관리자 전용 (생성, 수정, 삭제)
    DefaultDto.CreateResDto create(NoticeDto.CreateReqDto param, Long reqUserId);
    void update(NoticeDto.UpdateReqDto param, Long reqUserId);
    void delete(NoticeDto.UpdateReqDto param, Long reqUserId);

    // 공통 (조회)
    NoticeDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto param, Long reqUserId);
    List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto param, Long reqUserId);
}
