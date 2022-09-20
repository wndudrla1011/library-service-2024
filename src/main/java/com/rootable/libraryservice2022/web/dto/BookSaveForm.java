package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Status;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
public class BookSaveForm {

    @NotNull
    private String title;

    @NotNull
    private String writer;

    @NotNull
    @Range(min = 1000, max = 100000)
    private Integer price;

    @NotNull
    @Max(999)
    private Integer stock;

    @NotNull
    private Status status;

}
