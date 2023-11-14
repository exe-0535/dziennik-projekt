package com.example.dziennik.controllers;

import com.example.dziennik.dao.UserDao;
import com.example.dziennik.model.User;
import com.example.dziennik.utils.DBUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private VBox loginPane;
    @FXML
    private Button logInButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    UserDao userDao = new UserDao();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Attempting login with button click
        logInButton.setOnAction(event -> {
            try {
                loginUser(event);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Attempting login with ENTER
        loginPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                logInButton.fire();
            }
        });
    }
    @FXML
    private void loginUser(ActionEvent event) throws IOException, InterruptedException {
        String email = emailField.getText();
        String pass = passwordField.getText();

        User user = validateLogin();

        if(user.getRole() == User.Role.ADMIN) {
            SceneController.getAdminScene(event);
            return;
        }

        if(user.getRole() == User.Role.TEACHER) {
            SceneController.getTeacherScene(event);
            return;
        }

        try {
            SceneController.getAdminScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    boolean validFields() {
        return !emailField.getText().isEmpty() && !passwordField.getText().isEmpty();
    }

    private User validateLogin() {
        try {
            User user = userDao.getConnectedUser(emailField.getText(), passwordField.getText());
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
