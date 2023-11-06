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
    private static final BasicDataSource dataSource = new BasicDataSource();

    // Change Scene Method
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String email) {

        Parent root = null;

        // Check if user is passing data in the form in case of adding more scenes in the future

                // In case of wanting to create an additional scene, put "else" or "else if" statement
                // for whatever scene you want to load.

        if(email != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

                // Check if user is admin or teacher and send data to the given controller
                if(email.equals("admin")) {
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
    public static void logInUser(ActionEvent event, String email, String password) {

        setPooling();

        PreparedStatement ps = null;
        ResultSet res = null;

        try (Connection conn = dataSource.getConnection()) {

            // Establish connection and send a db query
            ps = conn.prepareStatement("SELECT password, role FROM users WHERE email = ?");
            ps.setString(1, email);
            res = ps.executeQuery();

            // Check if user exists (if not, the statement will return false (0 records))
            if(!res.isBeforeFirst()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This user does not exist");
                alert.show();

            } else {

                while(res.next()) {

                    String retrievedPassword = res.getString("password");
                    String retrievedRole = res.getString("role");
                    if (retrievedPassword.equals(password) && retrievedRole.equals("admin")) {
                        changeScene(event, "admin-view.fxml", "Admin panel", email);
                    } else if (retrievedPassword.equals(password)) {
                        changeScene(event, "teacher-view.fxml", "Teacher panel", email);
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
            closeConnection(res, ps);
        }


    }

    // Close DB Connection Method
    public static void closeConnection(ResultSet res, PreparedStatement ps) {
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
    }

    // Set pooling parameters for the database
    public static void setPooling() {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/school_log");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        // Pooling settings
        dataSource.setInitialSize(3);
        dataSource.setMaxTotal(5);
        dataSource.setMaxIdle(5);
        dataSource.setMaxWaitMillis(5000);
    }

}
