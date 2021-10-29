package com.maximilianried.keychainconverter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.control.Alert;


public class Converter {

    public static void getFile(File selected) throws IOException {
        try {
            Reader reader = Files.newBufferedReader(Path.of(String.valueOf(selected)));
            CSVReader csvReader = new CSVReader(reader);
            bitwarden(csvReader);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public static void bitwarden(CSVReader csvReader) throws CsvValidationException, IOException {
        String[] nextRecord;
        String home = System.getProperty("user.home") + "/converted-for-keychain.csv";

        CSVWriter csvWriter = new CSVWriter(new FileWriter(home));
        csvWriter.writeNext(new String[]{"Title", "Url", "Username", "Password", "OTPAuth"});

        // TODO delete first line of imported file

        try {
            while ((nextRecord = csvReader.readNext()) != null) {
                if (!nextRecord[7].contains("http") && !nextRecord[7].equals("")) {
                    nextRecord[7] = "https://" + nextRecord[7];
                }
                csvWriter.writeNext(new String[]{nextRecord[3], nextRecord[7], nextRecord[8], nextRecord[9], nextRecord[10]});
            }

            csvWriter.close();
            exportSuccess();

        } catch (ArrayIndexOutOfBoundsException e) {
            Files.delete(Paths.get(home));
            fileError();
        }
    }

    public static void exportSuccess() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Keychain Converter");
        alert.setHeaderText("Erfolgreich abgeschlossen");
        alert.setContentText("Vergiss nicht die Datei nach dem erfolgreichem Import zu löschen da diese nicht verschlüsselt ist!");
        alert.showAndWait();
    }

    public static void fileError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Keychain Converter");
        alert.setHeaderText("Datei konnte nicht gelesen werden");
        alert.setContentText("Überprüfe bitte, ob der richtige Passwort-Manager ausgewählt ist von dem die Datei stammt.");
        alert.showAndWait();
    }
}