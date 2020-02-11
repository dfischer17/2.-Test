package at.htlgkr.ticketingsystem;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TicketFileManagerTest {

    @Test
    public void ticketFileManager_load_something_simple() throws IOException {
        String initialString = TEST_FILE_SIMPLE;
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        TicketFileManager underTest = new TicketFileManager();
        List<Ticket> tickets = underTest.loadTickets(targetStream, getAllCategories());

        assertTrue(tickets.size() > 0);
    }

    @Test
    public void ticketFileManager_load_correctSize_simple() throws IOException {
        String initialString = TEST_FILE_SIMPLE;
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        TicketFileManager underTest = new TicketFileManager();
        List<Ticket> tickets = underTest.loadTickets(targetStream, getAllCategories());

        assertEquals(1, tickets.size());
    }

    @Test
    public void ticketFileManager_load_correctData_simple() throws IOException {
        String initialString = TEST_FILE_SIMPLE;
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        TicketFileManager underTest = new TicketFileManager();
        List<Ticket> tickets = underTest.loadTickets(targetStream, getAllCategories());

        TestUtils.ListTester<Ticket> lt = new TestUtils.ListTester();

        assertTrue(lt.listsEqual(tickets, getAllTickets().subList(0, 1)));
    }

    @Test
    public void ticketFileManager_load_something() throws IOException {
        String initialString = TEST_FILE;
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        TicketFileManager underTest = new TicketFileManager();
        List<Ticket> tickets = underTest.loadTickets(targetStream, getAllCategories());

        assertTrue(tickets.size() > 0);
    }

    @Test
    public void ticketFileManager_load_correctSize() throws IOException {
        String initialString = TEST_FILE;
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        TicketFileManager underTest = new TicketFileManager();
        List<Ticket> tickets = underTest.loadTickets(targetStream, getAllCategories());

        assertEquals(10, tickets.size());
    }

    @Test
    public void ticketFileManager_load_correctData() throws IOException {
        String initialString = TEST_FILE;
        InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
        TicketFileManager underTest = new TicketFileManager();
        List<Ticket> tickets = underTest.loadTickets(targetStream, getAllCategories());

        TestUtils.ListTester<Ticket> lt = new TestUtils.ListTester();

        assertTrue(lt.listsEqual(tickets, getAllTickets()));
    }

    @Test
    public void ticketFileManager_save_writesSomething() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TicketFileManager underTest = new TicketFileManager();
        underTest.saveTickets(os, getAllTickets().subList(0, 1));
        String result = new String(os.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);

        assertTrue(result.length() > 5);
    }

    @Test
    public void ticketFileManager_save_correctDatasetCount() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TicketFileManager underTest = new TicketFileManager();
        underTest.saveTickets(os, getAllTickets());
        String result = new String(os.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);

        assertEquals(10, countOccurances(result, "<dataset>"));
        assertEquals(10, countOccurances(result, "</dataset>"));
    }

    @Test
    public void ticketFileManager_save_correctDescCount() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TicketFileManager underTest = new TicketFileManager();
        underTest.saveTickets(os, getAllTickets());
        String result = new String(os.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);

        assertEquals(10, countOccurances(result, "<desc>"));
        assertEquals(10, countOccurances(result, "</desc>"));
    }

    @Test
    public void ticketFileManager_save_correctData() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        TicketFileManager underTest = new TicketFileManager();
        underTest.saveTickets(os, getAllTickets());
        String result = new String(os.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);

        String[] checkData = STORE_TEST.split("\n");

        for(String cData : checkData) {
            assertTrue(result.contains(cData));
        }
    }

    private int countOccurances(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){
            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

    public List<Category> getAllCategories() {
        List<Category> result = new ArrayList<>();

        Category cat1 = new Category(1, "Backlog");
        Category cat2 = new Category(2, "Next");
        Category cat3 = new Category(3, "To Do");
        Category cat4 = new Category(4, "Done");

        result.add(cat1);
        result.add(cat2);
        result.add(cat3);
        result.add(cat4);

        return result;
    }

    public List<Ticket> getAllTickets() {
        Category c0 = new Category(1, "Backlog");
        LocalDateTime s0 = LocalDateTime.parse("2020-01-20T12:12");
        LocalDateTime e0 = LocalDateTime.parse("2020-01-21T14:14");
        Ticket t0 = new Ticket("E-Mail Synchronisation", "Die E-Mail Synchronisation gehoert implementiert.", s0, e0, c0);
        Category c1 = new Category(1, "Backlog");
        LocalDateTime s1 = LocalDateTime.parse("2020-01-22T12:12");
        LocalDateTime e1 = LocalDateTime.parse("2020-01-23T00:14");
        Ticket t1 = new Ticket("Mobile Darstellung", "Die Website soll auch auf handys funktionieren", s1, e1, c1);
        Category c2 = new Category(1, "Backlog");
        LocalDateTime s2 = LocalDateTime.parse("2020-01-24T04:32");
        LocalDateTime e2 = LocalDateTime.parse("2020-01-25T23:14");
        Ticket t2 = new Ticket("GUI Anpassungen", "Es muessen diverse GUI Anpassungen durchgefuehrt werden.", s2, e2, c2);
        Category c3 = new Category(2, "Next");
        LocalDateTime s3 = LocalDateTime.parse("2020-01-24T04:32");
        LocalDateTime e3 = LocalDateTime.parse("2020-01-25T23:14");
        Ticket t3 = new Ticket("Authentifizierung im Backend", "Im Backend soll der Authentifizierungsmechanismus implementiert werden.", s3, e3, c3);
        Category c4 = new Category(2, "Next");
        LocalDateTime s4 = LocalDateTime.parse("2020-01-29T08:22");
        LocalDateTime e4 = LocalDateTime.parse("2020-01-29T09:14");
        Ticket t4 = new Ticket("Authentifizierung im Frontent", "Im Frontend soll der Authentifizierungsmechanismus implementiert werden.", s4, e4, c4);
        Category c5 = new Category(3, "To Do");
        LocalDateTime s5 = LocalDateTime.parse("2020-01-29T08:22");
        LocalDateTime e5 = LocalDateTime.parse("2020-01-29T09:14");
        Ticket t5 = new Ticket("REST API implementieren", "Alle REST Endpunkte implementieren", s5, e5, c5);
        Category c6 = new Category(4, "Done");
        LocalDateTime s6 = LocalDateTime.parse("2020-01-01T20:22");
        LocalDateTime e6 = LocalDateTime.parse("2020-01-01T21:14");
        Ticket t6 = new Ticket("Infrastruktur aufsetzen", "Gesamte Infrastruktur fuer das Backend aufsetzen auf AWS.", s6, e6, c6);
        Category c7 = new Category(4, "Done");
        LocalDateTime s7 = LocalDateTime.parse("2019-12-30T08:22");
        LocalDateTime e7 = LocalDateTime.parse("2019-12-31T09:14");
        Ticket t7 = new Ticket("Ticketing-System suchen", "Es soll ein ordentlichen Ticketing-System gefunden werden.", s7, e7, c7);
        Category c8 = new Category(4, "Done");
        LocalDateTime s8 = LocalDateTime.parse("2020-12-29T08:22");
        LocalDateTime e8 = LocalDateTime.parse("2020-12-29T09:14");
        Ticket t8 = new Ticket("Ticketing-System entscheiden", "Alle gefundenen Ticketing systeme evaluieren und fuer eines entscheiden.", s8, e8, c8);
        Category c9 = new Category(4, "Done");
        LocalDateTime s9 = LocalDateTime.parse("2020-12-22T08:22");
        LocalDateTime e9 = LocalDateTime.parse("2020-12-31T09:14");
        Ticket t9 = new Ticket("Ticketing-System implementieren", "Da kein geeignetes Ticketing-System gefunden wurde, selbst eins implementieren", s9, e9, c9);

        List<Ticket> result = new ArrayList<>();

        result.add(t0);
        result.add(t1);
        result.add(t2);
        result.add(t3);
        result.add(t4);
        result.add(t5);
        result.add(t6);
        result.add(t7);
        result.add(t8);
        result.add(t9);

        return result;
    }

    public static final String STORE_TEST = "title=E-Mail Synchronisation\n" +
            "Die E-Mail Synchronisation gehoert implementiert.\n" +
            "start=20.01.2020 12:12\n" +
            "end=21.01.2020 14:14\n" +
            "category_id=1\n" +
            "title=Mobile Darstellung\n" +
            "Die Website soll auch auf handys funktionieren\n" +
            "start=22.01.2020 12:12\n" +
            "end=23.01.2020 00:14\n" +
            "category_id=1\n" +
            "title=GUI Anpassungen\n" +
            "Es muessen diverse GUI Anpassungen durchgefuehrt werden.\n" +
            "start=24.01.2020 04:32\n" +
            "end=25.01.2020 23:14\n" +
            "category_id=1\n" +
            "title=Authentifizierung im Backend\n" +
            "Im Backend soll der Authentifizierungsmechanismus implementiert werden.\n" +
            "start=24.01.2020 04:32\n" +
            "end=25.01.2020 23:14\n" +
            "category_id=2\n" +
            "title=Authentifizierung im Frontent\n" +
            "Im Frontend soll der Authentifizierungsmechanismus implementiert werden.\n" +
            "start=29.01.2020 08:22\n" +
            "end=29.01.2020 09:14\n" +
            "category_id=2\n" +
            "title=REST API implementieren\n" +
            "Alle REST Endpunkte implementieren\n" +
            "start=29.01.2020 08:22\n" +
            "end=29.01.2020 09:14\n" +
            "category_id=3\n" +
            "title=Infrastruktur aufsetzen\n" +
            "Gesamte Infrastruktur fuer das Backend aufsetzen auf AWS.\n" +
            "start=01.01.2020 20:22\n" +
            "end=01.01.2020 21:14\n" +
            "category_id=4\n" +
            "title=Ticketing-System suchen\n" +
            "Es soll ein ordentlichen Ticketing-System gefunden werden.\n" +
            "start=30.12.2019 08:22\n" +
            "end=31.12.2019 09:14\n" +
            "category_id=4\n" +
            "title=Ticketing-System entscheiden\n" +
            "Alle gefundenen Ticketing systeme evaluieren und fuer eines entscheiden.\n" +
            "start=29.12.2020 08:22\n" +
            "end=29.12.2020 09:14\n" +
            "category_id=4\n" +
            "title=Ticketing-System implementieren\n" +
            "Da kein geeignetes Ticketing-System gefunden wurde, selbst eins implementieren\n" +
            "start=22.12.2020 08:22\n" +
            "end=31.12.2020 09:14\n" +
            "category_id=4";

    public static final String TEST_FILE = "<dataset>\n" +
            "   title=E-Mail Synchronisation\n" +
            "   <desc>\n" +
            "      Die E-Mail Synchronisation gehoert implementiert.\n" +
            "   </desc>\n" +
            "   start=20.01.2020 12:12\n" +
            "   end=21.01.2020 14:14\n" +
            "   category_id=1\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Mobile Darstellung\n" +
            "   <desc>\n" +
            "      Die Website soll auch auf handys funktionieren\n" +
            "   </desc>\n" +
            "   start=22.01.2020 12:12\n" +
            "   end=23.01.2020 00:14\n" +
            "   category_id=1\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=GUI Anpassungen\n" +
            "   <desc>\n" +
            "      Es muessen diverse GUI Anpassungen durchgefuehrt werden.\n" +
            "   </desc>\n" +
            "   start=24.01.2020 04:32\n" +
            "   end=25.01.2020 23:14\n" +
            "   category_id=1\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Authentifizierung im Backend\n" +
            "   <desc>\n" +
            "      Im Backend soll der Authentifizierungsmechanismus implementiert werden.\n" +
            "   </desc>\n" +
            "   start=24.01.2020 04:32\n" +
            "   end=25.01.2020 23:14\n" +
            "   category_id=2\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Authentifizierung im Frontent\n" +
            "   <desc>\n" +
            "      Im Frontend soll der Authentifizierungsmechanismus implementiert werden.\n" +
            "   </desc>\n" +
            "   start=29.01.2020 08:22\n" +
            "   end=29.01.2020 09:14\n" +
            "   category_id=2\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=REST API implementieren\n" +
            "   <desc>\n" +
            "      Alle REST Endpunkte implementieren\n" +
            "   </desc>\n" +
            "   start=29.01.2020 08:22\n" +
            "   end=29.01.2020 09:14\n" +
            "   category_id=3\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Infrastruktur aufsetzen\n" +
            "   <desc>\n" +
            "      Gesamte Infrastruktur fuer das Backend aufsetzen auf AWS.\n" +
            "   </desc>\n" +
            "   start=01.01.2020 20:22\n" +
            "   end=01.01.2020 21:14\n" +
            "   category_id=4\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Ticketing-System suchen\n" +
            "   <desc>\n" +
            "      Es soll ein ordentlichen Ticketing-System gefunden werden.\n" +
            "   </desc>\n" +
            "   start=30.12.2019 08:22\n" +
            "   end=31.12.2019 09:14\n" +
            "   category_id=4\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Ticketing-System entscheiden\n" +
            "   <desc>\n" +
            "      Alle gefundenen Ticketing systeme evaluieren und fuer eines entscheiden.\n" +
            "   </desc>\n" +
            "   start=29.12.2020 08:22\n" +
            "   end=29.12.2020 09:14\n" +
            "   category_id=4\n" +
            "</dataset>\n" +
            "\n" +
            "<dataset>\n" +
            "   title=Ticketing-System implementieren\n" +
            "   <desc>\n" +
            "      Da kein geeignetes Ticketing-System gefunden wurde, selbst eins implementieren\n" +
            "   </desc>\n" +
            "   start=22.12.2020 08:22\n" +
            "   end=31.12.2020 09:14\n" +
            "   category_id=4\n" +
            "</dataset>";

    public static final String TEST_FILE_SIMPLE = "<dataset>\n" +
            "   title=E-Mail Synchronisation\n" +
            "   <desc>\n" +
            "      Die E-Mail Synchronisation gehoert implementiert.\n" +
            "   </desc>\n" +
            "   start=20.01.2020 12:12\n" +
            "   end=21.01.2020 14:14\n" +
            "   category_id=1\n" +
            "</dataset>";

}
