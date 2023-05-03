package com.svalero.basket.api.controller;

import com.opencsv.CSVWriter;
import com.svalero.basket.api.model.DataPlayer;
import com.svalero.basket.api.model.DataTeam;
import com.svalero.basket.api.model.Player;
import com.svalero.basket.api.model.Team;
import com.svalero.basket.api.task.PlayersTask;
import com.svalero.basket.api.task.TeamsTask;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppController {

    public Button btShowTeams;
    public Button btShowPlayers;
    public Button btDeletePlayer;
    public Button btExport;

    public TextField tfInputPlayer;
    //public TextArea teamsArea;
    public TextArea playersArea;
    public ListView listTeams;
    public ProgressBar pbProgress;
    public List<String> teams;
    public List<Player> players;
    public ObservableList<Object> resultsTeams;
    private TeamsTask teamsTask;
    private PlayersTask playersTask;

    //inicializar para usar la LisView de Teams
    @FXML
    public void initialize() {
        pbProgress.setProgress(0.0);
        resultsTeams = FXCollections.observableArrayList();
        this.listTeams.setItems(this.resultsTeams); //listTeams se subscribe al ObervableList
    }

    //Evento que se ejecuta al presionar el boton de ver equipos
    @FXML
    public void showAllTeams(ActionEvent event) {
        this.teams = new ArrayList<String>();

        Consumer<DataTeam> userTeam = (dataTeam -> {
            Platform.runLater(() -> {
                pbProgress.setProgress(0.5);
            });

            for (Team team : dataTeam.getData()) {
                this.resultsTeams.add("Nombre: " + team.getName() + " Abrev: " + team.getAbbreviation() + " Conference: " + team.getConference() + " Division: " +
                        team.getDivision() + " Nombre completo: " + team.getFull_name());
            }
            Platform.runLater(() -> {
                pbProgress.setProgress(1.0);
            });
        });

        this.teamsTask = new TeamsTask(userTeam);
        new Thread(teamsTask).start();
    }

    //Evento que se ejecuta al presionar el boton de ver jugadores
    @FXML
    public void showAllPlayers(ActionEvent event) {
        this.players = new ArrayList<>();
        playersArea.setText("");

        Consumer<DataPlayer> userPlayer = (dataPlayer -> {
            Platform.runLater(() -> {
                pbProgress.setProgress(0.5);
            });

            String previousText;
            previousText = playersArea.getText() + "\n";
            for (Player player : dataPlayer.getData()) {
                Thread.sleep(100);
                playersArea.appendText("ID: " + player.getId() + " Nombre: " + player.getFirst_name() + " Apellido: " + player.getLast_name() + " Posicion: " + player.getPosition() + "Equipo: " + player.getTeam() + "\n\n");
                this.players.add(player);
            }

            Platform.runLater(() -> {
                this.pbProgress.setProgress(1.0);
            });
        });
        this.playersTask = new PlayersTask(userPlayer, pbProgress);
        new Thread(playersTask).start();
    }

    //opcion de borrar jugador usando el array almacenado y no la API
    @FXML
    public void deletePlayer(ActionEvent event) {
        int playerIndex = Integer.parseInt(tfInputPlayer.getText());
        this.players.remove(playerIndex);
        this.playersArea.setText("");
        for (Player player : this.players) {
            this.playersArea.appendText(player.getId() + " " + player.getFirst_name() + " " + player.getLast_name() + " " + player.getPosition() + " " + player.getTeam() + "\n\n");
        }
    }

    //Opcion de exportar a CSV los equipos
    @FXML
    public void exportarCSV(ActionEvent event) {
        File outputFile = new File(System.getProperty("user.dir") + System.getProperty("file.separator")
                + "equipos.csv");
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> file = new ArrayList<String[]>();

            for (Object team : this.resultsTeams) {
                file.add(new String[]{team.toString()});
            }
            csvWriter.writeAll(file);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



