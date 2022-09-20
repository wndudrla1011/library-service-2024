package com.rootable.libraryservice2022.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String writer;

    @NotBlank
    @Range(min = 1000, max = 100000)
    private Integer price;

    @NotNull
    @Max(999)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Builder
    public Book(String title, String writer, Integer price, Integer stock, Status status) {
        this.title = title;
        this.writer = writer;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

}
