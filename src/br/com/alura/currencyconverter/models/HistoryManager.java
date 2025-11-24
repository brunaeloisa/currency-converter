package br.com.alura.currencyconverter.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryManager {
    public static List<Conversion> readJsonLog() {
        try {
            FileReader reader = new FileReader("conversion-log.json");
            Gson gson = new Gson();
            Conversion[] conversionList = gson.fromJson(reader, Conversion[].class);
            return new ArrayList<>(Arrays.asList(conversionList));
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public static void saveJsonLog(List<Conversion> conversions) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("conversion-log.json");
            writer.write(gson.toJson(conversions));
            writer.close();
        } catch (IOException e) {
            System.out.println("\nErro ao salvar o histórico: " + e.getMessage());
        }
    }
}
