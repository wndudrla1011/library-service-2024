package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title = :title")
    Book findByTitle(@Param("title") String title);

}
