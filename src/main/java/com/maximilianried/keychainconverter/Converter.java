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

        try {
            while ((nextRecord = csvReader.readNext()) != null) {
                if (!nextRecord[7].contains("http") && !nextRecord[7].equals("")) {
                    nextRecord[7] = "https://" + nextRecord[7];
                }
                csvWriter.writeNext(new String[]{nextRecord[3], nextRecord[7], nextRecord[8], nextRecord[9], nextRecord[10]});
            }

            csvWriter.close();
            Alerts.alertWindow(Alert.AlertType.INFORMATION,"Abgeschlossen", "Vergiss nicht die Datei nach dem erfolgreichem Import zu löschen");

        } catch (ArrayIndexOutOfBoundsException e) {
            Files.delete(Paths.get(home));
            Alerts.alertWindow(Alert.AlertType.ERROR, "Einlesefehler", "Überprüfe bitte, ob die Datei von Bitwarden stammt.");
        }
    }
}