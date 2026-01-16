package com.minzzzun.bookdart_be.dto;

import com.minzzzun.bookdart_be.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class UserDto {
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto {
        String username;
        String password;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto {
        String username;
        String password;

        public User toEntity(){
            return User.of(username, password);
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto {
        String password;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto {
        String username;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.DetailReqDto {
        String username;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        String username;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        String username;
    }


}
