package com.svalero.basket.api;

import com.svalero.basket.api.controller.AppController;
import com.svalero.basket.api.util.R;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("main-basket.fxml"));
        loader.setController(new AppController());
        ScrollPane mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("BasketAPI");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
