package com.maximilianried.keychainconverter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    protected void openFile(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        filechooser.setTitle("Exportierte CSV Datei auswählen");

        try {
            File selected = filechooser.showOpenDialog(stage);
            Converter.getFile(selected);
        } catch (IOException e) {
            selectError();
        }
    }

    public static void selectError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Keychain Converter");
        alert.setHeaderText("Datei nicht gefunden");
        alert.setContentText("Die Datei konnte nicht gefunden werden, bittw wähle eine Datei!");
        alert.showAndWait();
    }
}