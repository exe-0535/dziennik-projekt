package com.example.dziennik.controllers;

import com.example.dziennik.dao.LessonDao;
import com.example.dziennik.helpers.CurrentUser;
import com.example.dziennik.helpers.LessonRow;
import com.example.dziennik.model.Lesson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private Button logoutBtn;
    @FXML
    private TableView tableView;

    LessonDao lessonDao = new LessonDao();

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


        ObservableList<LessonRow> lessonRows = FXCollections.observableArrayList();
        List<Lesson> lessons = lessonDao.getLessonsForTeacher();
        Collections.sort(lessons);

        for(int i = 0; i < 10; i++) {
            LessonRow row = new LessonRow();
            row.setNr(i + 1);
            row.setGodzina(row.getGodzina(i+1));
            row.setMonday(row.getMonday(i+1,lessons));
            row.setTuesday(row.getTuesday(i+1,lessons));
            row.setWednesday(row.getWednesday(i+1,lessons));
            row.setThursday(row.getThursday(i+1,lessons));
            row.setFriday(row.getFriday(i+1,lessons));
            lessonRows.add(row);
        }


        tableView.setItems(lessonRows);
    }
}
