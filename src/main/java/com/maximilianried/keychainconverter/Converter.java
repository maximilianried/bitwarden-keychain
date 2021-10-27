package com.maximilianried.keychainconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Converter {

    public static void getFile(File selected) {
        try {
            Scanner sc = new Scanner(new File(String.valueOf(selected)));

            sc.useDelimiter(";");

            while(sc.hasNext()) {
                System.out.print(sc.next()+ "|");
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file selected");
        }
    }

}
