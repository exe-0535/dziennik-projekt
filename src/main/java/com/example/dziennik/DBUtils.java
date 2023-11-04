package com.example.dziennik;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.sql.*;

public class DBUtils {

    // Log In Method
    public static void logInUser(ActionEvent event, String username, String password) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        try {

            // Establish connection and send a db query
            conn = DriverManager.getConnection("jdbc:mysql://3306/schooldiary", "root", "");
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
                        // Change scene to a admin panel
                    } else if (retrievedPassword.equals(password)) {
                        // Change scene to a teacher's view
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
