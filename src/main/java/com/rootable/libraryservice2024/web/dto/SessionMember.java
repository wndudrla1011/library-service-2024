package com.rootable.libraryservice2024.web.dto;

import com.rootable.libraryservice2024.domain.Member;
import com.rootable.libraryservice2024.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//세션에 사용자 정보를 저장하기 위한 DTO + 직렬화 기능
@Data
@NoArgsConstructor
public class SessionMember implements Serializable {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String role;

    public SessionMember(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.email = member.getEmail();
        this.role = member.getRole();
    }

}
