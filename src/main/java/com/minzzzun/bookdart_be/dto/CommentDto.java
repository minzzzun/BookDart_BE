package com.minzzzun.bookdart_be.dto;

import com.minzzzun.bookdart_be.domain.Comment;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class CommentDto {


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        Long postId;
        Long userId;
        String content;


        public Comment toEntity(){
            return Comment.of(postId, userId, content);
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String content;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailReqDto {
        Long userId;
        Long postId;
        String username;
        String content;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long postId;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long postId;

    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long postId;
    }
}
