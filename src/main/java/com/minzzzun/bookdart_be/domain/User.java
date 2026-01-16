package com.minzzzun.bookdart_be.domain;

import com.minzzzun.bookdart_be.dto.DefaultDto;
import com.minzzzun.bookdart_be.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Entity
public class User extends AuditingFields {
    String username;
    String password;

    protected User(){}

    private User(Boolean deleted, String username, String password) {
        this.deleted = deleted;
        this.username = username;
        this.password = password;
    }

    public static User of(String username, String password){
        return new User(false, username, password);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

    public void update(UserDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted()); }
        if(param.getPassword() != null){ setPassword(param.getPassword()); }
    }
}
