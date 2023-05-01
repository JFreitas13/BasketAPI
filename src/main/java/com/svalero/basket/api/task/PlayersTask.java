package com.svalero.basket.api.task;

import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.service.BasketService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class PlayersTask extends Task<Integer> {

    public Consumer<DataPlayer> userPlayer;

    public PlayersTask(Consumer<DataPlayer> userPlayer) {
        this.userPlayer = userPlayer;
    }

    @Override
    protected Integer call() throws Exception {

        BasketService basketService = new BasketService();
        basketService.getPlayers().subscribe(userPlayer);
        return null;
    }
}
