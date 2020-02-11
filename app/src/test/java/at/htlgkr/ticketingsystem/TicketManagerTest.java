package at.htlgkr.ticketingsystem;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TicketManagerTest {
    @Test
    public void ticketManager_constructor() {
        TicketManager underTest = new TicketManager(getAllCategories());

        TestUtils.ListTester<Category> lt = new TestUtils.ListTester<>();

        assertTrue(lt.listsEqual(underTest.getCategories(), getAllCategories()));
    }

    @Test
    public void ticketManager_addTicket() {
        TicketManager underTest = new TicketManager(getAllCategories());

        underTest.addTicket(getAllTickets().get(0));

        TestUtils.ListTester<Ticket> lt = new TestUtils.ListTester<>();

        assertTrue(lt.listsEqual(underTest.getTicketList(), getAllTickets().subList(0, 1)));
    }

    @Test
    public void ticketManager_setTicketList() {
        TicketManager underTest = new TicketManager(getAllCategories());

        underTest.setTicketList(getAllTickets());

        TestUtils.ListTester<Ticket> lt = new TestUtils.ListTester<>();

        assertTrue(lt.listsEqual(underTest.getTicketList(), getAllTickets()));
    }

    @Test
    public void ticketManager_getStandardCategory() {
        TicketManager underTest = new TicketManager(getAllCategories());

        TestUtils.ListTester<Ticket> lt = new TestUtils.ListTester<>();

        assertTrue(lt.categoryEquals(underTest.getStandardCategory(), getAllCategories().get(0)));
    }

    @Test
    public void ticketManager_getCategories() {
        TicketManager underTest = new TicketManager(getAllCategories());

        TestUtils.ListTester<Category> lt = new TestUtils.ListTester<>();

        assertTrue(lt.listsEqual(underTest.getCategories(), getAllCategories()));
    }

    @Test
    public void ticketManger_getCategoryById() {
        TicketManager underTest = new TicketManager(getAllCategories());

        TestUtils.ListTester<Category> lt = new TestUtils.ListTester<>();

        assertTrue(lt.categoryEquals(underTest.getCategoryById(1), getAllCategories().get(0)));
        assertTrue(lt.categoryEquals(underTest.getCategoryById(2), getAllCategories().get(1)));
        assertTrue(lt.categoryEquals(underTest.getCategoryById(3), getAllCategories().get(2)));
        assertTrue(lt.categoryEquals(underTest.getCategoryById(4), getAllCategories().get(3)));
    }

    @Test
    public void ticketManger_getTicketListForCategoryId() {
        TicketManager underTest = new TicketManager(getAllCategories());

        TestUtils.ListTester<Ticket> lt = new TestUtils.ListTester<>();
        underTest.setTicketList(getAllTickets());

        assertTrue(lt.listsEqual(underTest.getTicketListForCategoryId(1), getTicketsByCategoryId(1)));
        assertTrue(lt.listsEqual(underTest.getTicketListForCategoryId(2), getTicketsByCategoryId(2)));
        assertTrue(lt.listsEqual(underTest.getTicketListForCategoryId(3), getTicketsByCategoryId(3)));
        assertTrue(lt.listsEqual(underTest.getTicketListForCategoryId(4), getTicketsByCategoryId(4)));

    }

    @Test
    public void ticketManager_getNextCategory_simple() {
        TicketManager underTest = new TicketManager(getAllCategories());

        Category next = underTest.getNextCategory(getAllCategories().get(0));

        TestUtils.ListTester<Category> lt = new TestUtils.ListTester<>();

        assertTrue(lt.categoryEquals(next, getAllCategories().get(1)));
    }

    @Test
    public void ticketManager_getNextCategory_end() {
        TicketManager underTest = new TicketManager(getAllCategories());

        Category next = underTest.getNextCategory(getAllCategories().get(3));

        TestUtils.ListTester<Category> lt = new TestUtils.ListTester<>();

        assertTrue(lt.categoryEquals(next, getAllCategories().get(3)));
    }

    @Test
    public void ticketManger_calcTotalHours_calculatesSomething() {
        TicketManager underTest = new TicketManager(getAllCategories());
        underTest.setTicketList(getAllTicketsHourTest());

        assertTrue(underTest.calcTotalHours() > 0);
    }

    @Test
    public void ticketManger_calcTotalHours_correct() {
        TicketManager underTest = new TicketManager(getAllCategories());
        underTest.setTicketList(getAllTicketsHourTest());

        assertEquals(4, underTest.calcTotalHours());
    }

    @Test
    public void ticketManger_calcHoursByCategory_calculatesSomething() {
        TicketManager underTest = new TicketManager(getAllCategories());
        underTest.setTicketList(getAllTicketsHourTest());

        assertTrue(underTest.calcHoursByCategory(new Category(1, "Backlog")) > 0);
    }

    @Test
    public void ticketManger_calcHoursByCategory_correct() {
        TicketManager underTest = new TicketManager(getAllCategories());
        underTest.setTicketList(getAllTicketsHourTest());

        assertEquals(2, underTest.calcHoursByCategory(new Category(1, "Backlog")));
    }


    public List<Ticket> getTicketsByCategoryId(int id) {
        List<Ticket> result = new ArrayList<>();

        for(Ticket cTicket : getAllTickets()) {
            if(cTicket.getCategory().getId() == id)
                result.add(cTicket);
        }

        return result;
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

    public List<Ticket> getAllTicketsHourTest() {
        Category c1 = new Category(1, "Backlog");
        LocalDateTime s1 = LocalDateTime.parse("2020-01-20T12:12");
        LocalDateTime e1 = LocalDateTime.parse("2020-01-20T13:12");
        Ticket t1 = new Ticket("Mobile Darstellung", "Die Website soll auch auf handys funktionieren", s1, e1, c1);
        Category c2 = new Category(1, "Backlog");
        LocalDateTime s2 = LocalDateTime.parse("2020-01-20T12:12");
        LocalDateTime e2 = LocalDateTime.parse("2020-01-20T13:12");
        Ticket t2 = new Ticket("GUI Anpassungen", "Es muessen diverse GUI Anpassungen durchgefuehrt werden.", s2, e2, c2);
        Category c3 = new Category(2, "Next");
        LocalDateTime s3 = LocalDateTime.parse("2020-01-20T12:12");
        LocalDateTime e3 = LocalDateTime.parse("2020-01-20T13:12");
        Ticket t3 = new Ticket("Authentifizierung im Backend", "Im Backend soll der Authentifizierungsmechanismus implementiert werden.", s3, e3, c3);
        Category c4 = new Category(2, "Next");
        LocalDateTime s4 = LocalDateTime.parse("2020-01-20T12:12");
        LocalDateTime e4 = LocalDateTime.parse("2020-01-20T13:12");
        Ticket t4 = new Ticket("Authentifizierung im Frontent", "Im Frontend soll der Authentifizierungsmechanismus implementiert werden.", s4, e4, c4);

        List<Ticket> result = new ArrayList<>();

        result.add(t1);
        result.add(t2);
        result.add(t3);
        result.add(t4);

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
}
