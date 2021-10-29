package com.maximilianried.keychainconverter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;


public class Converter {

    public static void getFile(File selected) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(Path.of(String.valueOf(selected)));
            CSVReader csvReader = new CSVReader(reader);
        ) {
            bitwarden(csvReader);
        } catch (CsvValidationException e) {
            System.out.println("Failed read file");
            e.printStackTrace();
        }
    }

    public static void bitwarden(CSVReader csvReader) throws CsvValidationException, IOException {
        String[] nextRecord;String home = System.getProperty("user.home");
        String homePath = home + "/converted.csv";
        CSVWriter csvWriter = new CSVWriter(new FileWriter(homePath));

        while ((nextRecord = csvReader.readNext()) != null) {
            if (!nextRecord[7].contains("http") && !nextRecord[7].equals("")) {
                nextRecord[7] = "https://" + nextRecord[7];
            }

            csvWriter.writeNext(new String[]{nextRecord[7], nextRecord[8], nextRecord[9], nextRecord[10]});
        }

        csvWriter.close();
    }
}
