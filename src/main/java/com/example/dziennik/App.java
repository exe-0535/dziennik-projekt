package com.example.dziennik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 486);

        stage.setTitle("Dziennik zastępstw");
        stage.setScene(scene);
        stage.setMinHeight(720);
        stage.setMinWidth(1280);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}