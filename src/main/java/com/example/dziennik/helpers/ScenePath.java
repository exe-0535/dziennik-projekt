package com.example.dziennik.helpers;

public enum ScenePath {

    LOGIN("/view/login-view.fxml"),
    TEACHER("/view/teacher-view.fxml"),
    ADMIN("/view/admin-view.fxml"),
    STUDENT("/view/student-view.fxml");


    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
