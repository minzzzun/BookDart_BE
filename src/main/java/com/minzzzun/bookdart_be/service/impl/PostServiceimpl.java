package com.minzzzun.bookdart_be.service.impl;

import com.minzzzun.bookdart_be.domain.Post;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PostDto;
import com.minzzzun.bookdart_be.exception.NoMatchingDataException;
import com.minzzzun.bookdart_be.mapper.PostMapper;
import com.minzzzun.bookdart_be.repository.PostRepository;
import com.minzzzun.bookdart_be.service.PermittedService;
import com.minzzzun.bookdart_be.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceimpl implements PostService {
    final PostRepository postRepository;
    final PostMapper postMapper;
    final PermittedService permittedService;
    final String target = "post";

    @Override
    public DefaultDto.CreateResDto create(PostDto.CreateReqDto param, Long reqUserId) {
        if(param.getUserId() == null || param.getUserId() == (long) 0) {
            param.setUserId(reqUserId);
        }

        if(!param.getUserId().equals(reqUserId)) {
            permittedService.isPermitted(reqUserId, target, 110);
        }
        return postRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(PostDto.UpdateReqDto param, Long reqUserId) {
        Post post = postRepository.findById(param.getId()).orElseThrow(() -> new NoMatchingDataException("no data"));
        if(!post.getUserId().equals(reqUserId)){
            permittedService.isPermitted(reqUserId, target, 120);
        }
        post.update(param);
        postRepository.save(post);
    }

    @Override
    public void delete(PostDto.UpdateReqDto param, Long reqUserId) {
        update(PostDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public PostDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        //본인 정보인 경우 확인
        Post post = postRepository.findById(param.getId())
                .orElseThrow(() -> new NoMatchingDataException("no data"));

        if(!post.getUserId().equals(reqUserId)){
            permittedService.isPermitted(reqUserId, target, 200);
        }
        return postMapper.detail(param.getId());
    }

    @Override
    public PostDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    @Override
    public List<PostDto.DetailResDto> list(PostDto.ListReqDto param, Long reqUserId) {
        return detailList(postMapper.list(param), reqUserId);
    }

    public List<PostDto.DetailResDto> detailList(List<PostDto.DetailResDto> list, Long reqUserId){
        List<PostDto.DetailResDto> newList = new ArrayList<>();
        for(PostDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(PostDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(postMapper.pagedListCount(param));
        res.setList(detailList(postMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PostDto.DetailResDto> scrollList(PostDto.ScrollListReqDto param, Long reqUserId) {
        param.init();
        return detailList(postMapper.scrollList(param), reqUserId);
    }
}
