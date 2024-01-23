package com.rootable.libraryservice2024.repository;

import com.rootable.libraryservice2024.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
