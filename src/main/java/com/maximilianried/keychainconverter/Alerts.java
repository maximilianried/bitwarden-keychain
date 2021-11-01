package com.maximilianried.keychainconverter;

import javafx.scene.control.Alert;

public class Alerts {

    public static void alertWindow(Alert.AlertType type, String title, String text) {
        Alert alert = new Alert(type);
        alert.setTitle("Keychain Converter");
        alert.setHeaderText(title);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
