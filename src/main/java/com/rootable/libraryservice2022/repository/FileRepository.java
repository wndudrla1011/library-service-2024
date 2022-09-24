package com.rootable.libraryservice2022.repository;

import com.rootable.libraryservice2022.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
