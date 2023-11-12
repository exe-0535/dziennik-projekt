package com.example.dziennik.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationController.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 486);
        scene.getStylesheets().add(getClass().getResource("styles/login-style.css").toExternalForm());

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