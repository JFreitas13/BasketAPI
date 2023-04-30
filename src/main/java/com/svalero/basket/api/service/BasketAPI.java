package com.svalero.basket.api.service;

import com.svalero.basket.api.model.Data;
import com.svalero.basket.api.model.Player;
import com.svalero.basket.api.model.Team;
import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface BasketAPI {

    @GET("api/v1/teams")
    Observable<List<Data>> getInformationTeam();

    @GET("api/v1/players")
    Observable<List<Data>> getInformacionPlayer();
}
