package com.svalero.basket.api.controller;

import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.model.DataTeam;
import com.svalero.basket.api.model.Player;
import com.svalero.basket.api.model.Team;
import com.svalero.basket.api.task.PlayersTask;
import com.svalero.basket.api.task.TeamsTask;
import io.reactivex.functions.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;


public class AppController {

    public Button btShowTeams;
    public Button btShowPlayers;
    public TextArea teamsArea;
    public TextArea playersArea;

    private TeamsTask teamsTask;
    private PlayersTask playersTask;

    public List<String> teams;
    public List<String> players;

    @FXML
    public void showAllTeams(ActionEvent event) {

      this.teams = new ArrayList<String>();
        teamsArea.setText("");

        Consumer<DataTeam> userTeam = (dataTeam -> {
            String previousText;
            previousText = teamsArea.getText() + "\n";
            //Thread.sleep(100);
            this.teamsArea.setText(teamsArea.getText() + "\n" + dataTeam.getData());
            this.teams.add(String.valueOf(dataTeam.getData()));
        });

        teamsTask = new TeamsTask(userTeam);
        new Thread(teamsTask).start();
    }

    @FXML
    public void showAllPlayers(ActionEvent event) {
        this.players = new ArrayList<String>();
        playersArea.setText("");

        Consumer<DataPlayer> userPlayer = (dataPlayer -> {
            String previousText;
            previousText = playersArea.getText() + "\n";
            this.playersArea.setText(playersArea.getText() + "\n" + dataPlayer.getData() /*+ "\n" + player.getFirst_name() + "\n" + player.getLast_name() + "\n" + player.getTeam()*/);
            this.players.add(String.valueOf(dataPlayer.getData()));
        });

        playersTask = new PlayersTask(userPlayer);
        new Thread(playersTask).start();
    }
}
