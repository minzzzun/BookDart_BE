package com.minzzzun.bookdart_be.service;

import com.minzzzun.bookdart_be.dto.PermissionDto;
import org.springframework.stereotype.Service;

@Service
public interface PermittedService {
    void isPermitted(Long userId, String target, int func);
    boolean permitted(PermissionDto.PermittedReqDto param);
}
