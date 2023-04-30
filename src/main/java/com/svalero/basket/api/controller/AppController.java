package com.svalero.basket.api.controller;

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

        Consumer<Team> user = (team -> {
            String previousText;
            previousText = teamsArea.getText() + "\n";
            //Thread.sleep(100);
            this.teamsArea.setText(teamsArea.getText() + "\n" + team.getId() + "\n" + team.getAbbreviation() + "\n" + team.getCity() + "\n" + team.getConference() + "\n" + team.getDivision() + "\n" + team.getFull_name() + "\n" + team.getName());
            this.teams.add(team.getId() + team.getAbbreviation() + team.getCity() + team.getConference() + team.getDivision() + team.getFull_name() + team.getName());
        });

        teamsTask = new TeamsTask(user);
        new Thread(teamsTask).start();
    }

    @FXML
    public void showAllPlayers(ActionEvent event) {
        this.players = new ArrayList<String>();
        playersArea.setText("");

        Consumer<Player> user = (player -> {
            String previousText;
            previousText = playersArea.getText() + "\n";
            this.playersArea.setText(playersArea.getText() + "\n" + player.getId() + "\n" + player.getFirst_name() + "\n" + player.getLast_name() + "\n" + player.getTeam());
            this.players.add(player.getId() + player.getTeam().getName());
        });

        playersTask = new PlayersTask(user);
        new Thread(playersTask).start();
    }
}
