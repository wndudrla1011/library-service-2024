package com.rootable.libraryservice2024.repository;

import com.rootable.libraryservice2024.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
