package com.minzzzun.bookdart_be.dto;

import com.minzzzun.bookdart_be.domain.Post;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PostDto {


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        Long userId;
        String title;
        String content;
        String img;

        public Post toEntity(){
            return Post.of(userId, title, content, img);
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String title;
        String content;
        String img;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long userId;
        String title;
        String content;
        String img;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.DetailReqDto {
        String title;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        String title;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        String title;
    }


}
