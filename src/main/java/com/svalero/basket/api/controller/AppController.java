package com.svalero.basket.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.model.DataTeam;
import com.svalero.basket.api.model.Player;
import com.svalero.basket.api.model.Team;
import com.svalero.basket.api.task.PlayersTask;
import com.svalero.basket.api.task.TeamsTask;
import io.reactivex.functions.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppController {

    public Button btShowTeams;
    public Button btShowPlayers;
    public Button btDeleteTeam;
    public Button btExport;
    public TextField tfIdTeam;
    //public TextArea teamsArea;
    public TextArea playersArea;

    public ListView listTeams;

    private TeamsTask teamsTask;
    private PlayersTask playersTask;

    public List<String> teams;
    public List<String> players;

    public ObservableList<Object> resultsTeams;


    @FXML
    public void initialize() {
        resultsTeams = FXCollections.observableArrayList();
        this.listTeams.setItems(this.resultsTeams);
    }
    @FXML
    public void showAllTeams(ActionEvent event) {
      this.teams = new ArrayList<String>();

        Consumer<DataTeam> userTeam = (dataTeam -> {
            for (Team team : dataTeam.getData()) {
                this.resultsTeams.add(team.getName());
            }
            //String previousText;
            //previousText = teamsArea.getText() + "\n";
            //Thread.sleep(100);
            //this.teamsArea.setText(teamsArea.getText() + "\n" + dataTeam.getData());
            //this.teams.add(String.valueOf(dataTeam.getData()));
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
                for (Player player : dataPlayer.getData()) {
                    playersArea.appendText(player.getId() + " " + player.getFirst_name() + " " + player.getLast_name() + " " + player.getPosition() + " " + player.getTeam() + "\n\n");
                    this.players.add(playersArea.getText() + "\n" + dataPlayer.getData()+ "\n" + player.getId() + "\n\n" + player.getFirst_name() + "\n" + player.getLast_name() + "\n" + player.getPosition() + "\n" + player.getTeam());
                }
        });
        playersTask = new PlayersTask(userPlayer);
        new Thread(playersTask).start();
    }

    @FXML
    public void deleteTeam(ActionEvent event) {
        int teamIndex = Integer.parseInt(tfIdTeam.getText());

        if (teamIndex < this.resultsTeams.size()) { // Comprueba que el índice sea válido
            this.resultsTeams.remove(teamIndex); // Elimina el nombre del equipo de la lista de resultados
            this.listTeams.setItems(FXCollections.observableArrayList((this.resultsTeams))); // Actualiza la ListView);

        }
        }

    @FXML
    public void exportarCSV(ActionEvent event) {
        File outputFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "equipos.csv");
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> file = new ArrayList<String[]>();
//            for (String team : this.resultsTeams) {
//                file.add(new String[] {team});
//            }
            for (Object team : this.resultsTeams) {
                file.add(new String[] {team.toString()});
            }
            csvWriter.writeAll(file);
            csvWriter.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }



