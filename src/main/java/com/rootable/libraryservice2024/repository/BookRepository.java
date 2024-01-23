package com.rootable.libraryservice2024.repository;

import com.rootable.libraryservice2024.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title = :title")
    Book findByTitle(@Param("title") String title);

}
