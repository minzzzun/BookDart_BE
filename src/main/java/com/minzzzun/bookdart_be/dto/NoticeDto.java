package com.minzzzun.bookdart_be.dto;

import com.minzzzun.bookdart_be.domain.Notice;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class NoticeDto {


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        Long adminId;
        String title;
        String content;
        Boolean pinned;

        public Notice toEntity(){
            return Notice.of(adminId, title, content, pinned);
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String title;
        String content;
        Boolean pinned;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long adminId;
        String title;
        String content;
        Boolean pinned;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.DetailReqDto {
        String title;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        String title;
        Boolean pinned;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        String title;
        Boolean pinned;
    }


}
