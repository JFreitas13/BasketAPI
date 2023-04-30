package com.svalero.basket.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int  id;
    private String first_name;
    private String last_name;
    private String position;
    private int height_feet;
    private int height_inches;
    private int weight_pounds;
    private Team team;
}
