package com.svalero.basket.api.controller;

import com.svalero.basket.api.model.Team;
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
    public TextArea teamsArea;

    private TeamsTask teamsTask;

    public List<String> teams;

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
}
