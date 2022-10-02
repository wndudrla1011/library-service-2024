package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
