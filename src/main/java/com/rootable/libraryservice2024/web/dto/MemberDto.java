package com.rootable.libraryservice2024.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rootable.libraryservice2024.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @JsonProperty(access = WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$",
            message = "비밀번호 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 하며 8~16자리여야 합니다.")
    @Size(min = 3, max = 100)
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min = 3, max = 50)
    private String nickname;

    private Set<AuthorityDto> authorities;

    public static MemberDto from(Member member) {
        if (member == null) return null;

        return MemberDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .authorities(member.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

}
