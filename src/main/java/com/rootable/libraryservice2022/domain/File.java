package com.rootable.libraryservice2022.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false)
    private String originFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Builder
    public File(Long id, String originFilename, String filename, String filePath) {
        this.id = id;
        this.originFilename = originFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
