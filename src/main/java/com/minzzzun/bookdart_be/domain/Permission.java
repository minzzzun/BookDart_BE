package com.minzzzun.bookdart_be.domain;

import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.PermissionDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(indexes = {@Index(columnList = "deleted")})
@Entity
public class Permission extends AuditingFields {
    String title;
    String content;

    protected Permission(){}
    private Permission(String title, String content){
        this.title = title;
        this.content = content;
    }
    public static Permission of(String title, String content){
        return new Permission(title, content);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

    public void update(PermissionDto.UpdateReqDto param) {
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getTitle() != null){ setTitle(param.getTitle()); }
        if(param.getContent() != null){ setContent(param.getContent()); }
    }
}
