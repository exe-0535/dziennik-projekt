module com.example.dziennik {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires java.naming;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires jdk.unsupported;

    exports com.example.dziennik.controllers;
    opens com.example.dziennik.controllers to javafx.fxml;
    exports com.example.dziennik.utils;
    opens com.example.dziennik.utils to javafx.fxml;
    exports com.example.dziennik;
    opens com.example.dziennik to javafx.fxml;
    exports com.example.dziennik.helpers;
    opens com.example.dziennik.helpers to javafx.base;

    opens com.example.dziennik.model to org.hibernate.orm.core;
}