package com.minzzzun.bookdart_be.dto;

import com.minzzzun.bookdart_be.domain.Like;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class LikeDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        Long postId;
        Long userId;

        public Like toEntity(){
            return Like.of(postId, userId);
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        Long postId;
        Long userId;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long postId;
        Long userId;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.DetailReqDto {
        String title;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ToggleReqDto {
        Long postId;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ToggleResDto {
        Long postId;
        Boolean liked; // 현재 좋아요 상태
        Long likeCount; // 해당 포스트의 총 좋아요 수
    }

    // 포스트별 좋아요 조회 요청 DTO
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ListByPostReqDto {
        Long postId;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long postId;
        Long userId;
    }
}
