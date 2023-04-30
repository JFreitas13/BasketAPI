package com.svalero.basket.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    private List<Player> players;
    private List<Team> teams;
    private Meta meta;


}
