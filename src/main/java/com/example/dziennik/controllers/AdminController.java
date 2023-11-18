package com.example.dziennik.controllers;

import com.example.dziennik.dao.LessonDao;
import com.example.dziennik.dao.UserDao;
import com.example.dziennik.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Long.parseLong;

public class AdminController implements Initializable {

    @FXML
    private Button logoutBtn;

    @FXML
    private ChoiceBox<Integer> nr;
    @FXML
    private ChoiceBox<String> oldMail;
    @FXML
    private ChoiceBox<String> day;
    @FXML
    private ChoiceBox<String> newMail;
    @FXML
    private TextField subject;
    @FXML
    private TextField classnr;
    @FXML
    private Button updateBtn;

    private Integer[] nrs = {1,2,3,4,5,6,7,8,9,10};
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    LessonDao lessonDao = new LessonDao();
    public void initialize(URL location, ResourceBundle resource) {
        nr.getItems().addAll(nrs);
        day.getItems().addAll(days);
        oldMail.getItems().addAll(Objects.requireNonNull(UserDao.getMails()));
        newMail.getItems().addAll(Objects.requireNonNull(UserDao.getMails()));


        logoutBtn.setOnAction(event -> {
            try {
                SceneController.getLoginScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateBtn.setOnAction(event -> {
            try {
                System.out.println(nr.getValue());
                System.out.println(UserDao.getUserIdByEmail(oldMail.getValue()));
                System.out.println(UserDao.getUserIdByEmail(newMail.getValue()));
                System.out.println(day.getValue());
                System.out.println(subject.getText());
                System.out.println(parseLong(classnr.getText()));

                lessonDao.updateLesson(nr.getValue(), UserDao.getUserIdByEmail(oldMail.getValue()), UserDao.getUserIdByEmail(newMail.getValue()), day.getValue(), subject.getText(), parseLong(classnr.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
