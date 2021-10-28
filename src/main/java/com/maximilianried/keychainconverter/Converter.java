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

        while ((nextRecord = csvReader.readNext()) != null) {
            System.out.println("Folder : " + nextRecord[0]);
            System.out.println("Favorite : " + nextRecord[1]);
            System.out.println("Type : " + nextRecord[2]);
            System.out.println("Name : " + nextRecord[3]);
            System.out.println("Notes : " + nextRecord[4]);
            System.out.println("Fields : " + nextRecord[5]);
            System.out.println("Reprompt : " + nextRecord[6]);

            if (!nextRecord[7].contains("http") && !nextRecord[7].equals("")) {
                nextRecord[7] = "https://" + nextRecord[7];
            }

            System.out.println("URL : " + nextRecord[7]);
            System.out.println("Username : " + nextRecord[8]);
            System.out.println("Password : " + nextRecord[9]);
            System.out.println("TOTP : " + nextRecord[10]);
            System.out.println("==========================");

            /*
            try (
                Writer writer = Files.newBufferedWriter(Paths.get(homePath));

                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
            ) {
                String[] headerRecord = {"Name", "URL", "Password", "TOTP"};
                csvWriter.writeNext(headerRecord);

                csvWriter.writeNext(new String[]{nextRecord[3]});
                csvWriter.writeNext(new String[]{nextRecord[7]});
                csvWriter.writeNext(new String[]{nextRecord[9]});
                csvWriter.writeNext(new String[]{nextRecord[10]});
            }
            */
        }


    }
}
