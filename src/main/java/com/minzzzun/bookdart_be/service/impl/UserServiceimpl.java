package com.minzzzun.bookdart_be.service.impl;

import com.minzzzun.bookdart_be.domain.User;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.UserDto;
import com.minzzzun.bookdart_be.exception.NoMatchingDataException;
import com.minzzzun.bookdart_be.mapper.UserMapper;
import com.minzzzun.bookdart_be.repository.UserRepository;
import com.minzzzun.bookdart_be.security.AuthService;
import com.minzzzun.bookdart_be.service.PermittedService;
import com.minzzzun.bookdart_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final PermittedService permittedService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final AuthService authService;
    final String target = "user";


    @Override
    public DefaultDto.CreateResDto signup(UserDto.CreateReqDto param, Long reqUserId) {
        return create(param, reqUserId);
    }

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 110);
        User user = userRepository.findByUsername(param.getUsername());
        if (user != null) {
            throw new RuntimeException("already exists");
        }
        param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
        return userRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(UserDto.UpdateReqDto param, Long reqUserId) {
        if(param.getId() == 0){ param.setId(reqUserId); }
        if(!param.getId().equals(reqUserId)){
            permittedService.isPermitted(reqUserId, target, 120);
        }
        User user = userRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        if(param.getPassword() != null){ param.setPassword(bCryptPasswordEncoder.encode(param.getPassword())); }
        user.update(param);

        userRepository.save(user);
    }

    @Override
    public void delete(UserDto.UpdateReqDto param, Long reqUserId) {
        update(UserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public UserDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        //본인 정보인 경우 확인
        if(!param.getId().equals(reqUserId)){
            permittedService.isPermitted(reqUserId, target, 200);
        }
        UserDto.DetailResDto res = userMapper.detail(param.getId());
        return res;
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        if(param.getId() == 0){ param.setId(reqUserId); }
        return get(param, reqUserId);
    }

    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto param, Long reqUserId) {
        return detailList(userMapper.list(param), reqUserId);
    }

    public List<UserDto.DetailResDto> detailList(List<UserDto.DetailResDto> list, Long reqUserId){
        List<UserDto.DetailResDto> newList = new ArrayList<>();
        for(UserDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }


    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(userMapper.pagedListCount(param));
        res.setList(detailList(userMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param, Long reqUserId) {
        param.init();
        return detailList(userMapper.scrollList(param), reqUserId);
    }
}
