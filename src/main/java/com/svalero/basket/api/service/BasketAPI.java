package com.svalero.basket.api.service;

import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.model.DataTeam;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BasketAPI {

    //buscar todos los equipos
    @GET("api/v1/teams")
    Observable<DataTeam> getInformationTeam();

    //buscar todos los jugadores
    @GET("api/v1/players")
    Observable<DataPlayer> getInformacionPlayer();
}
