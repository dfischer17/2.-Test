package at.htlgkr.ticketingsystem;

import android.icu.util.LocaleData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static android.media.MediaCodec.MetricsConstants.MODE;
import static java.lang.reflect.Modifier.PRIVATE;
import static java.nio.file.StandardOpenOption.APPEND;

public class TicketFileManager {
    // todo
    public List<Ticket> loadTickets(InputStream fis, List<Category> categories) throws IOException {
        // todo implement this
        List<Ticket> tickets = new ArrayList<>();
        BufferedReader bin = new BufferedReader(new InputStreamReader(fis));
        List<String> fileLines = new ArrayList<>();

        // Zeilen einlesen
        String line;
        try {
            while ((line = bin.readLine()) != null) {
                // Zeile
                fileLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileLines.removeIf(s -> s.equals(""));

        // Zeilen durchlaufen
        for (int i = 1; i < fileLines.size(); i++) {
            // Ticket erstellen
            String title = "";
            String desc = "";
            LocalDateTime start = null;
            LocalDateTime end = null;
            Category category = null;

            if (i + 7 < fileLines.size()) {

                // Titel aus zweiter Zeile einlesen
                String[] temp = fileLines.get(i).split("=");
                title = temp[1];

                // Description aus Dritter Zeile einlesen
                desc = fileLines.get(i + 2);

                // start aus fuenfter Zeile einlesen
                String[] temp4 = fileLines.get(i + 4).split("=");
                start = LocalDateTime.parse(temp4[1], DateTimeFormatter.ofPattern(Configuration.DATE_TIME_FORMAT));


                // end aus sechster Zeile einlesen
                String[] temp2 = fileLines.get(i + 5).split("=");
                end = LocalDateTime.parse(temp2[1], DateTimeFormatter.ofPattern(Configuration.DATE_TIME_FORMAT));

                // category aus siebter Zeile einlesen
                String categoryLine = fileLines.get(i + 6);
                String[] temp3 = categoryLine.split("=");
                int id = Integer.valueOf(temp3[1]);
                category = categories.get(id);


                // String title, String description, LocalDateTime start, LocalDateTime end, Category category
                tickets.add(new Ticket(title, desc, start, end, category));
            }


        }
        return tickets;
    }

    public void saveTickets(OutputStream fos, List<Ticket> tickets) {
        // implement this
        String output = "";
        StringBuilder outputBuilder = new StringBuilder(output);

        for (Ticket curTicket : tickets) {
            // dataset
            outputBuilder.append("<dataset>" + "\n");

            // title
            outputBuilder.append("title=" + curTicket.getTitle() + "\n");

            // desc
            outputBuilder.append("<desc>" + "\n");
            outputBuilder.append(curTicket.getDescription());
            outputBuilder.append("</desc>" + "\n");

            // start
            outputBuilder.append("start=" + curTicket.getStart().format(DateTimeFormatter.ofPattern(Configuration.DATE_TIME_FORMAT)) + "\n");

            // end
            outputBuilder.append("end=" + curTicket.getEnd().format(DateTimeFormatter.ofPattern(Configuration.DATE_TIME_FORMAT)) + "\n");

            // category
            outputBuilder.append("category_id=" + curTicket.getCategory().getId() + "\n");

            // dataset schliessen
            outputBuilder.append("</dataset>" + "\n");
        }


        PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
        out.println(outputBuilder.toString());
        out.flush();
        out.close();
    }
}