package com.svalero.basket.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    private int totalPages;
    private int currentPage;
    private int nextPage;
    private int perPage;
    private int totalCount;

}
