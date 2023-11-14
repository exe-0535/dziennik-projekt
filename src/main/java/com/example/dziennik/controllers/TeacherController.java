package com.example.dziennik.controllers;

import com.example.dziennik.helpers.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Zalogowano jako: " + CurrentUser.getCurrentUser().getEmail());

        logoutBtn.setOnAction(event -> {
            try {
                SceneController.getLoginScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
