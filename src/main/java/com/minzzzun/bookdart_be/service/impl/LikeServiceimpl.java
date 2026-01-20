package com.minzzzun.bookdart_be.service.impl;

import com.minzzzun.bookdart_be.domain.Like;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.LikeDto;
import com.minzzzun.bookdart_be.exception.NoMatchingDataException;
import com.minzzzun.bookdart_be.mapper.LikeMapper;
import com.minzzzun.bookdart_be.repository.LikeRepository;
import com.minzzzun.bookdart_be.service.LikeService;
import com.minzzzun.bookdart_be.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeServiceimpl implements LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;
    private final PermittedService permittedService;  // 추가
    private final String target = "like";  // 추가


    @Override
    public LikeDto.ToggleResDto toggle(LikeDto.ToggleReqDto param, Long reqUserId) {
        Long postId = param.getPostId();

        Optional<Like> existingLike = likeRepository.findByPostIdAndUserId(postId, reqUserId);

        boolean liked;
        if (existingLike.isPresent()) { // 이미 좋아요가 있으면 deleted 상태 토글
            Like like = existingLike.get();
            like.setDeleted(!like.getDeleted());
            likeRepository.save(like);
            liked = !like.getDeleted();
        } else { // 좋아요가 없으면 새로 생성
            Like newLike = Like.of(postId, reqUserId);
            likeRepository.save(newLike);
            liked = true;
        }

        Long likeCount = countByPost(postId);

        return LikeDto.ToggleResDto.builder()
                .postId(postId)
                .liked(liked)
                .likeCount(likeCount)
                .build();
    }

    @Override
    public LikeDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        Like like = likeRepository.findById(param.getId())
                .orElseThrow(() -> new NoMatchingDataException("no data"));

        if (!like.getUserId().equals(reqUserId)) {
            permittedService.isPermitted(reqUserId, target, 200);
        }

        return likeMapper.detail(param.getId());
    }

    @Override
    public List<LikeDto.DetailResDto> listByPost(LikeDto.ListByPostReqDto param, Long reqUserId) {
        return likeMapper.listByPost(param);
    }

    @Override
    public Long countByPost(Long postId) {
        return likeRepository.countByPostIdAndDeletedFalse(postId);
    }

    @Override
    public Boolean isLiked(Long postId, Long userId) {
        return likeRepository.existsByPostIdAndUserIdAndDeletedFalse(postId, userId);
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(LikeDto.PagedListReqDto param, Long reqUserId) {
        permittedService.isPermitted(reqUserId, target, 200);

        DefaultDto.PagedListResDto res = param.init(likeMapper.pagedListCount(param));
        res.setList(likeMapper.pagedList(param));
        return res;
    }
}
