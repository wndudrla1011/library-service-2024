package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Member;
import com.rootable.libraryservice2022.domain.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostsDto {

    private List<Posts> posts;
    private MemberDto member;


    @Builder
    public PostsDto(List<Posts> posts, MemberDto member) {
        this.posts = posts;
        this.member = member;
    }

}
