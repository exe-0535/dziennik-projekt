package com.example.dziennik.helpers;

public enum ScenePath {

    LOGIN("/login-view.fxml"),
    TEACHER("/teacher-view.fxml"),
    ADMIN("/admin-view.fxml");


    private final String path;

    private ScenePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
