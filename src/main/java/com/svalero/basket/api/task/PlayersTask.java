package com.svalero.basket.api.task;

import com.svalero.basket.api.model.Player;
import com.svalero.basket.api.service.BasketService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;

public class PlayersTask extends Task<Integer> {

    public Consumer<Player> userPlayer;

    public PlayersTask(Consumer<Player> userPlayer) {
        this.userPlayer = userPlayer;
    }

    @Override
    protected Integer call() throws Exception {

        BasketService basketService = new BasketService();
        basketService.getPlayers().subscribe(userPlayer);
        return null;
    }
}
