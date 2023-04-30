package com.svalero.basket.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    private int total_pages;
    private int current_page;
    private int next_page;
    private int per_age;
    private int total_count;

}
