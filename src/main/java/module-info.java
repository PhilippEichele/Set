module com.example.set {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens SetGame to javafx.fxml;
    exports SetGame;
}