package com.minzzzun.bookdart_be.service;

import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PermissionDto;
import com.minzzzun.bookdart_be.dto.PermissiondetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissiondetailService {

    PermissiondetailDto.AllowResDto allow(PermissionDto.ExistReqDto param, Long reqUserId);
    List<PermissiondetailDto.DetailResDto> access(PermissionDto.ExistReqDto param, Long reqUserId);

    DefaultDto.CreateResDto toggle(PermissiondetailDto.ToggleReqDto param, Long reqUserId);
    /**/
    DefaultDto.CreateResDto create(PermissiondetailDto.CreateReqDto param, Long reqUserId);
    void update(PermissiondetailDto.UpdateReqDto param, Long reqUserId);
    void delete(DefaultDto.DeleteReqDto param, Long reqUserId);
    void deleteList(DefaultDto.DeleteListReqDto param, Long reqUserId);
    PermissiondetailDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<PermissiondetailDto.DetailResDto> list(PermissiondetailDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(PermissiondetailDto.PagedListReqDto param, Long reqUserId);
    List<PermissiondetailDto.DetailResDto> scrollList(PermissiondetailDto.ScrollListReqDto param, Long reqUserId);
}
