package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {



}
