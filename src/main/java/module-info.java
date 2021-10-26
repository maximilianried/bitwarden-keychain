module com.maximilianried.keychainconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.maximilianried.keychainconverter to javafx.fxml;
    exports com.maximilianried.keychainconverter;
}