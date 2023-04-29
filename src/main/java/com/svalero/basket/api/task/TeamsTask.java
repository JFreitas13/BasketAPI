package com.svalero.basket.api.task;

import com.svalero.basket.api.model.Team;
import com.svalero.basket.api.service.BasketService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;


public class TeamsTask extends Task<Integer> {

    public Consumer<Team> user;

    public TeamsTask(Consumer<Team> user) {
        this.user = user;
    }

    @Override
    protected Integer call() throws Exception {
        BasketService basketService = new BasketService();
        basketService.getTeams().subscribe(user);
        return null;
    }
}
