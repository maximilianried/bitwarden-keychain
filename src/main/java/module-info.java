module com.maximilianried.keychainconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;

    opens com.maximilianried.keychainconverter to javafx.fxml;
    exports com.maximilianried.keychainconverter;
}