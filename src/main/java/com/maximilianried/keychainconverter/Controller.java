package com.maximilianried.keychainconverter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

public class Controller {
    @FXML
    protected void openFile(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        filechooser.setTitle("Exportierte CSV Datei auswählen");
        File selected = filechooser.showOpenDialog(stage);

        System.out.println(selected);
    }
}