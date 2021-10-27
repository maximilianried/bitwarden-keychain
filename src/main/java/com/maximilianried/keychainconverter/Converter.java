package com.maximilianried.keychainconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


public class Converter {

    public static void getFile(File selected) throws IOException {
        try {
            Scanner sc = new Scanner(new File(String.valueOf(selected)));

            try (CSVReader reader = new CSVReader(new FileReader(String.valueOf(selected)))) {
                List<String[]> r = reader.readAll();
                r.forEach(x -> System.out.println(Arrays.toString(x)));
            }

            sc.close();
        } catch (FileNotFoundException | CsvException e) {
            System.out.println("No file selected");
        }
    }

}
