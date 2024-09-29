package com.movie.award.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public void readExcelFile() throws IOException {
        String excelFilePath = ".\\datafiles\\movielist (12) (2) (3).csv";
        String line = "";
        String delimiter = ";";

        try(BufferedReader br = new BufferedReader(new FileReader(excelFilePath))){
            while((line = br.readLine()) != null){
                String[] columns = line.split(delimiter);
                System.out.println(columns);
            }
        }
    }
}
