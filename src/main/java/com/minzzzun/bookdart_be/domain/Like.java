package com.minzzzun.bookdart_be.domain;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(
        indexes = {
                @Index(columnList = "deleted"),
                @Index(columnList = "postId"),
                @Index(columnList = "userId"),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"postId", "userId"})
        }
)
@Entity
public class Like extends AuditingFields {
    Long postId;
    Long userId;

    protected Like(){}

    private Like(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public static Like of(Long postId, Long userId) {
        return new Like(postId, userId);
    }

    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

}
