module com.example.dziennik {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.dziennik to javafx.fxml;
    exports com.example.dziennik;
}