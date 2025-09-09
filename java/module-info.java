module com.example.attackonthelivingmodules {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;

    opens com.example.attackofthelivingmodules to javafx.fxml;
    exports com.example.attackofthelivingmodules;
    exports com.example.gameplay;
    opens com.example.gameplay to javafx.fxml;
}