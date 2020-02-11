package at.htlgkr.ticketingsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationParser {
    public static List<Category> getCategories(InputStream is) throws IOException {
        // implement this method
        List<Category> categories = new ArrayList<>();
        BufferedReader bin = new BufferedReader(new InputStreamReader(is));

        // File einlesen
        String input = "";
        StringBuilder fileContent = new StringBuilder(input);
        String line;

        // erste Zeile ueberspringen
        bin.readLine();
        try {
            while ((line = bin.readLine()) != null) {
                // Zeile
                fileContent.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // White spaces und \n und {} entfernen
        input = fileContent.toString();
        //input = input.replaceAll(" " , "");
        input = input.replaceAll("\n", "");
        input = input.replaceAll("\\{", "");
        //input = input.replaceAll("}", "");

        // Einzelne Werte der Kategorien heraussplitten
        String[] categoryValuePairs = input.split(",");

        // Aus Werten Kategory Objekte erstellen
        for (String curCategory : categoryValuePairs) {
            // "categoryX":"name"
            int id = 0;
            String name = "";

            String[] categoryValues = curCategory.split(":");
            // category count ignorieren
            if (categoryValues[0].contains("Count")) continue;

            // id herausformatieren
            String idString = categoryValues[0];
            idString = idString.replaceAll("category", "");
            idString = idString.replaceAll("\"", "");
            String temp = idString;
            temp = temp.replaceAll(" ", "");
            temp = temp.replaceAll("\"", "");
            id = Integer.valueOf(temp);

            // name herausformatieren
            name = categoryValues[1];
            name = name.replaceAll("\"", "");
            name = name.replaceAll(" ", "");

            // aktuelle Kategory hinzufuegen
            categories.add(new Category(id, name));
        }

        return categories;

    }
}