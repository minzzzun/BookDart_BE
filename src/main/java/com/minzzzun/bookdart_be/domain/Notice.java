package com.minzzzun.bookdart_be.domain;


import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.NoticeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Table(indexes = {
 @Index(columnList = "deleted")
})
@Entity
public class Notice extends  AuditingFields {
    Long adminId;
    String title;
    String content;
    Boolean pinned;

    protected Notice() {}

    public Notice(Long adminId, String title, String content, Boolean pinned) {
        this.adminId = adminId;
        this.title = title;
        this.content = content;
        this.pinned = pinned != null ? pinned : false;
    }

    public static Notice of(Long adminId, String title, String content, Boolean pinned) {
        return new Notice(adminId, title, content, pinned);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

    public void update(NoticeDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted());}
        if(param.getTitle() != null){ setTitle(param.getTitle());}
        if(param.getContent() != null){ setContent(param.getContent());}
        if(param.getPinned() != null){ setPinned(param.getPinned());}
    }
}
