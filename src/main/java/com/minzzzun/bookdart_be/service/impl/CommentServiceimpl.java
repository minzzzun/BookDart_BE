package com.minzzzun.bookdart_be.service.impl;

import com.minzzzun.bookdart_be.domain.Comment;
import com.minzzzun.bookdart_be.domain.Post;
import com.minzzzun.bookdart_be.dto.CommentDto;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PostDto;
import com.minzzzun.bookdart_be.exception.NoMatchingDataException;
import com.minzzzun.bookdart_be.mapper.CommentMapper;
import com.minzzzun.bookdart_be.mapper.PostMapper;
import com.minzzzun.bookdart_be.repository.CommentRepository;
import com.minzzzun.bookdart_be.repository.PostRepository;
import com.minzzzun.bookdart_be.service.CommentService;
import com.minzzzun.bookdart_be.service.PermittedService;
import com.minzzzun.bookdart_be.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceimpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PermittedService permittedService;
    private final String target = "comment";

    @Override
    public DefaultDto.CreateResDto create(CommentDto.CreateReqDto param, Long reqUserId) {
        if (param.getUserId() == null || param.getUserId() == 0){
            param.setUserId(reqUserId);
        }

        if(!param.getUserId().equals(reqUserId)){
            permittedService.isPermitted(reqUserId, target, 110);
        }


        return commentRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(CommentDto.UpdateReqDto param, Long reqUserId) {
        Comment comment = commentRepository.findById(param.getId()).orElseThrow(()->new NoMatchingDataException("comment not found"));

        if(!comment.getUserId().equals(reqUserId)){
            permittedService.isPermitted(reqUserId, target, 120);
        }
        comment.update(param);
        commentRepository.save(comment);
    }

    @Override
    public void delete(CommentDto.UpdateReqDto param, Long reqUserId) {
        update(CommentDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    @Override
    public CommentDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return commentMapper.detail(param.getId());
    }

    @Override
    public List<CommentDto.DetailResDto> list(CommentDto.ListReqDto param, Long reqUserId) {
        return commentMapper.list(param);
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(CommentDto.PagedListReqDto param, Long reqUserId) {
        int count = commentMapper.pagedListCount(param);

        int totalpage = (count + param.getPerpage() - 1) / param.getPerpage();

        List<CommentDto.DetailResDto> list = commentMapper.pagedList(param);

        return DefaultDto.PagedListResDto
                .builder()
                .list(list)
                .totalpage(totalpage)
                .callpage(param.getCallpage())
                .perpage(param.getPerpage())
                .listsize(list.size())
                .build();
    }

    @Override
    public List<CommentDto.DetailResDto> scrollList(CommentDto.ScrollListReqDto param, Long reqUserId) {
        return commentMapper.scrollList(param);
    }
}
