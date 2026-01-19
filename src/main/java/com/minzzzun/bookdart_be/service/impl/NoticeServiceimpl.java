package com.minzzzun.bookdart_be.service.impl;

import com.minzzzun.bookdart_be.domain.Notice;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.NoticeDto;
import com.minzzzun.bookdart_be.exception.NoMatchingDataException;
import com.minzzzun.bookdart_be.mapper.NoticeMapper;
import com.minzzzun.bookdart_be.repository.NoticeRepository;
import com.minzzzun.bookdart_be.service.PermittedService;
import com.minzzzun.bookdart_be.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeServiceimpl implements NoticeService {
    final NoticeRepository noticeRepository;
    final NoticeMapper noticeMapper;
    final PermittedService permittedService;
    final String target = "notice";

    // ========== 관리자 전용 ==========

    @Override
    public DefaultDto.CreateResDto create(NoticeDto.CreateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 110);
        param.setAdminId(reqUserId);
        return noticeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(NoticeDto.UpdateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 120);
        Notice notice = noticeRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        notice.update(param);
        noticeRepository.save(notice);
    }

    @Override
    public void delete(NoticeDto.UpdateReqDto param, Long reqUserId) {
        update(NoticeDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    // ========== 공통 (조회) =========

    public NoticeDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        //본인 정보인 경우 확인
        Notice notice = noticeRepository.findById(param.getId())
                .orElseThrow(() -> new NoMatchingDataException("no data"));

        return noticeMapper.detail(param.getId());
    }

    @Override
    public NoticeDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    @Override
    public List<NoticeDto.DetailResDto> list(NoticeDto.ListReqDto param, Long reqUserId) {
        return detailList(noticeMapper.list(param), reqUserId);
    }

    public List<NoticeDto.DetailResDto> detailList(List<NoticeDto.DetailResDto> list, Long reqUserId){
        List<NoticeDto.DetailResDto> newList = new ArrayList<>();
        for(NoticeDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(NoticeDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(noticeMapper.pagedListCount(param));
        res.setList(detailList(noticeMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<NoticeDto.DetailResDto> scrollList(NoticeDto.ScrollListReqDto param, Long reqUserId) {
        param.init();
        return detailList(noticeMapper.scrollList(param), reqUserId);
    }
}
