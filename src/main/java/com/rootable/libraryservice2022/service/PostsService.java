package com.rootable.libraryservice2022.service;

import com.rootable.libraryservice2022.domain.Posts;
import com.rootable.libraryservice2022.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    public List<Posts> findPosts() {
        return postsRepository.findPosts();
    }

}
