package com.rootable.libraryservice2022.web.dto;

import com.rootable.libraryservice2022.domain.Status;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class BookUpdateForm {

    @NotNull
    @Range(min = 1000, max = 100000)
    private Integer price;

    //수정에서는 재고 범위 자유
    private Integer stock;

    @NotNull
    private Status status;

}
