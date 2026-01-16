package com.minzzzun.bookdart_be.service.impl;

import com.minzzzun.bookdart_be.dto.PermissionDto;
import com.minzzzun.bookdart_be.exception.NoPermissionException;
import com.minzzzun.bookdart_be.mapper.PermissionMapper;
import com.minzzzun.bookdart_be.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PermittedServiceimpl implements PermittedService {

    final PermissionMapper permissionMapper;

    @Override
    public void isPermitted(Long userId, String target, int func) {
        // -200인 경우에는, 그냥 무사 통과를 부탁한 것!!
        if(userId != (long) -200){
            if(!permitted(PermissionDto.PermittedReqDto.builder().userId(userId).target(target).func(func).build())){
                throw new NoPermissionException("no auth");
            }
        }
    }
    @Override
    public boolean permitted(PermissionDto.PermittedReqDto param) {
        return (permissionMapper.permitted(param) > 0);
        //return true;
    }
}
