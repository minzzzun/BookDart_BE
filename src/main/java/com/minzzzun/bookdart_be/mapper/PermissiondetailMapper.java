package com.minzzzun.bookdart_be.mapper;

import com.minzzzun.bookdart_be.dto.PermissionDto;
import com.minzzzun.bookdart_be.dto.PermissiondetailDto;

import java.util.List;

public interface PermissiondetailMapper {
	List<PermissiondetailDto.DetailResDto> access(PermissionDto.ExistReqDto params);
	/**/
	PermissiondetailDto.DetailResDto detail(Long id);
	List<PermissiondetailDto.DetailResDto> list(PermissiondetailDto.ListReqDto param);

	List<PermissiondetailDto.DetailResDto> pagedList(PermissiondetailDto.PagedListReqDto param);
	int pagedListCount(PermissiondetailDto.PagedListReqDto param);
	List<PermissiondetailDto.DetailResDto> scrollList(PermissiondetailDto.ScrollListReqDto param);
}