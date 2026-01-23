package com.minzzzun.bookdart_be.domain;


import com.minzzzun.bookdart_be.dto.CommentDto;
import com.minzzzun.bookdart_be.dto.DefaultDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "deleted"),
        @Index(columnList = "postId"),
        @Index(columnList = "userId")
})
@Entity
public class Comment extends AuditingFields {
    Long postId;
    Long userId;
    String content;

    protected  Comment(){}


    private Comment(Long postId, Long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    public static Comment of(Long postId, Long userId, String content) {
        return new Comment(postId, userId, content);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

    public void update(CommentDto.UpdateReqDto param){
        if (param.getContent() != null) { setContent(param.getContent()); }
    }

}
