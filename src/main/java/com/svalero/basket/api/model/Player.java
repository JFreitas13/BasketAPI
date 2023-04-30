package com.svalero.basket.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    int  id;
    String first_name;
    String last_name;
    String position;
    int height_feet;
    int height_inches;
    int weight_pounds;
    Team team;
}
