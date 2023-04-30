package com.svalero.basket.api.service;

import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.model.DataTeam;
import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface BasketAPI {

    @GET("api/v1/teams")
    Observable<DataTeam> getInformationTeam();

    @GET("api/v1/players")
    Observable<DataPlayer> getInformacionPlayer();
}
