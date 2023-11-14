package com.example.dziennik;

import com.example.dziennik.controllers.SceneController;
import com.example.dziennik.utils.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class App extends Application {

    @Override
    public void init() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneController.getInitialScene(stage);
    }

    public static void main(String[] args) {
        App.launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        HibernateUtil.shutdown();
    }

}