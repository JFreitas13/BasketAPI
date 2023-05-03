package com.svalero.basket.api.task;

import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.service.BasketService;
import io.reactivex.functions.Consumer;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class PlayersTask extends Task<Integer> {

    public Consumer<DataPlayer> userPlayer;
    public ProgressBar pbProgress;


    public PlayersTask(Consumer<DataPlayer> userPlayer, ProgressBar pbProgress) {
        this.userPlayer = userPlayer;
        this.pbProgress = pbProgress;
    }

    @Override
    protected Integer call() throws Exception {

        this.pbProgress.setVisible(true);
        BasketService basketService = new BasketService();
        basketService.getPlayers().subscribe(userPlayer);
        return null;
    }
}
