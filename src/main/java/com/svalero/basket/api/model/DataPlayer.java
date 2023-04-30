package com.svalero.basket.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPlayer {

    private List<Player> data;
    private Meta meta;
}
