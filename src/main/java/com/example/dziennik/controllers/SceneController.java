package com.example.dziennik.controllers;

import com.example.dziennik.App;
import com.example.dziennik.helpers.ScenePath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;


public class SceneController {
    private static double x;
    private static double y;

    private static Parent main;

    public static void getInitialScene(Stage stage) throws IOException {
        main = FXMLLoader.load((SceneController.class.getResource(ScenePath.LOGIN.getPath())));
        Scene scene = new Scene(main);
        controlDrag(stage);
        stage.setMinWidth(1280);
        stage.setMinHeight(720);
        stage.setTitle("School log");
        stage.setScene(scene);
        stage.show();
    }

    public static void getTeacherScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.TEACHER.getPath());
    }

    public static void getAdminScene(ActionEvent event) throws IOException {
        changeScreen(event, ScenePath.ADMIN.getPath());
    }



    private static void changeScreen(ActionEvent event, String path) throws IOException {
        main = FXMLLoader.load(SceneController.class.getResource(path));
        Scene visitScene = new Scene(main);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controlDrag(window);
        window.setScene(visitScene);
        window.show();
    }

    public static void controlDrag(Stage stage) {
        main.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x = stage.getX() - event.getScreenX();
                y = stage.getY() - event.getScreenY();
            }
        });
        main.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + x);
                stage.setY(event.getScreenY() + y);
            }
        });
    }

    public static void close(ActionEvent actionEvent) {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }




}
