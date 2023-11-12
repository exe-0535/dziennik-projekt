module com.example.dziennik {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.dbcp2;
    requires org.hibernate.orm.core;

    exports com.example.dziennik.controllers;
    opens com.example.dziennik.controllers to javafx.fxml;
    exports com.example.dziennik.utils;
    opens com.example.dziennik.utils to javafx.fxml;
}