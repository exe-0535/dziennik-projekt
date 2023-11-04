package com.example.dziennik;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class DBUtils {

    // Change Scene Method
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {

        Parent root = null;

        // Check if user is passing data in the form in case of adding more scenes in the future

                // In case of wanting to create an additional scene, put "else" or "else if" statement
                // for whatever scene you want to load.

        if(username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                // Check if user is admin or teacher and send data to the given controller
                if(username.equals("admin")) {
                    AdminController adminController = loader.getController();
                } else {
                    TeacherController teacherController = loader.getController();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    // Log In Method
    public static void logInUser(ActionEvent event, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/schooldiary");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        // Pooling settings
        dataSource.setInitialSize(5); // The initial number of connections
        dataSource.setMaxTotal(10); // Maximum number of active connections
        dataSource.setMaxIdle(5); // Maximum number of idle connections
        dataSource.setMaxWaitMillis(5000);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        try {

            // Establish connection and send a db query
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT password FROM users WHERE username = ?");
            ps.setString(1, username);
            res = ps.executeQuery();

            // Check if user exists (if not, the statement will return false (0 records))
            if(!res.isBeforeFirst()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This user does not exist");
                alert.show();

            } else {

                while(res.next()) {

                    String retrievedPassword = res.getString("password");
                    if (retrievedPassword.equals(password) && username.equals("admin")) {
                        changeScene(event, "admin-view.fxml", "Admin panel", username);
                    } else if (retrievedPassword.equals(password)) {
                        changeScene(event, "teacher-view.fxml", "Teacher panel", username);
                    } else {
                        // Inform user about the wrong password
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided password is incorrect");
                        alert.show();

                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(res, ps, conn);
        }


    }

    // Close DB Connection Method
    public static void closeConnection(ResultSet res, PreparedStatement ps, Connection conn) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
