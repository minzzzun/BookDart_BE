package com.minzzzun.bookdart_be.domain;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PostDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Table(indexes = {
        @Index(columnList = "deleted"),
        @Index(columnList = "userId")
})
@Entity
public class Post extends AuditingFields {
    Long userId;
    String title;
    String content;
    String img;

    protected  Post (){}

    private  Post(Long userId, String title, String content, String img) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public static Post of(Long userId, String title, String content, String img) {
        return new Post(userId, title, content, img);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

    public void update(PostDto.UpdateReqDto param) {
        if(param.getDeleted() != null) { setDeleted(param.getDeleted()); }
        if(param.getTitle() != null) { setTitle(param.getTitle()); }
        if(param.getContent() != null) { setContent(param.getContent()); }
        if(param.getImg() != null) { setImg(param.getImg()); }
    }

}
