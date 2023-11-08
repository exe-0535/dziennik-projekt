module com.example.dziennik {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.dbcp2;
    requires org.hibernate.orm.core;

    opens com.example.dziennik to javafx.fxml;
    exports com.example.dziennik;
}