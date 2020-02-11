package at.htlgkr.ticketingsystem;

import android.widget.ListView;
import android.widget.Spinner;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GuiTest {
    @Rule
    public ActivityTestRule<MainActivity> myActivityRule = new ActivityTestRule<>(MainActivity.class);

    @After
    public void cleanup() {
        resetFile();
    }

    @Test
    public void gui_categories_loadsSomething() {
        Spinner underTest = myActivityRule.getActivity().findViewById(R.id.sp_categories);

        List<Category> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Category);

            actual.add((Category) underTest.getAdapter().getItem(i));
        }

        assertTrue(actual.size() > 0);
    }

    @Test
    public void gui_categories_correctLength() {
        Spinner underTest = myActivityRule.getActivity().findViewById(R.id.sp_categories);

        List<Category> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Category);

            actual.add((Category) underTest.getAdapter().getItem(i));
        }

        assertEquals(4, actual.size());
    }

    @Test
    public void gui_categories_correctData() {
        Spinner underTest = myActivityRule.getActivity().findViewById(R.id.sp_categories);

        List<Category> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Category);

            actual.add((Category) underTest.getAdapter().getItem(i));
        }

        ListTester lt = new ListTester();
        assertTrue(lt.listsEqual(actual, getAllCategories()));
    }

    @Test
    public void gui_categories_correctSelectedAtStartup() {
        Spinner underTest = myActivityRule.getActivity().findViewById(R.id.sp_categories);

        assertTrue(underTest.getSelectedItem() instanceof Category);

        ListTester<Category> lt = new ListTester<>();

        assertTrue(lt.categoryEquals(getCategoryById(1), (Category) underTest.getSelectedItem()));
    }

    @Test
    public void gui_listView_startup_loadsSomething() {
        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        assertTrue(actual.size() > 0);
    }

    @Test
    public void gui_listView_startup_correctSize() {

        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        assertEquals(3, actual.size());
    }

    @Test
    public void gui_listView_startup_correctData() {

        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        List<Ticket> expected = getTicketsByCategoryId(1);

        ListTester<Ticket> lt = new ListTester<>();

        assertTrue(lt.listsEqual(actual, expected));
    }

    @Test
    public void gui_listView_categorychange_doesSomething() {

        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> before = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            before.add((Ticket) underTest.getAdapter().getItem(i));
        }

        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Next")).perform(click());

        List<Ticket> after = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            after.add((Ticket) underTest.getAdapter().getItem(i));
        }

        ListTester<Ticket> lt = new ListTester<>();

        assertFalse(lt.listsEqual(before, after));
    }

    @Test
    public void gui_listView_categorychange_correctSize() {

        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Next")).perform(click());

        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        assertEquals(2, actual.size());
    }

    @Test
    public void gui_listView_categorychange_correctData() {

        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Next")).perform(click());


        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        List<Ticket> expected = getTicketsByCategoryId(2);

        ListTester<Ticket> lt = new ListTester<>();

        assertTrue(lt.listsEqual(actual, expected));
    }

    @Test
    public void gui_listView_categorychange_correctData_all() {

        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Next")).perform(click());


        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        List<Ticket> expected = getTicketsByCategoryId(2);

        ListTester<Ticket> lt = new ListTester<>();

        assertTrue(lt.listsEqual(actual, expected));


        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("To Do")).perform(click());

        actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        expected = getTicketsByCategoryId(3);


        assertTrue(lt.listsEqual(actual, expected));


        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Done")).perform(click());

        actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        expected = getTicketsByCategoryId(4);


        assertTrue(lt.listsEqual(actual, expected));
    }

    @Test
    public void gui_addDataDialog_dialogOpens() {
        onView(withId(R.id.btn_addticket)).perform(click());
        onView(withText("Description")).check(matches(isDisplayed()));
    }

    @Test
    public void gui_addDataDialog_canCancel() {
        onView(withId(R.id.btn_addticket)).perform(click());
        onView(withId(android.R.id.button2)).perform(click());
    }

    @Test
    public void gui_addDataDialog_addTicket() {
        onView(withId(R.id.btn_addticket)).perform(click());

        onView(withId(R.id.et_title)).perform(typeText("Test Ticket"));
        onView(withId(R.id.et_description)).perform(typeText("Dies ist eine extensive description des Tickets und wird im Desc-Tag abgespeichert."));
        onView(withId(R.id.et_start)).perform(typeText("12.12.2020 12:30"));
        onView(withId(R.id.et_end)).perform(typeText("12.12.2020 15:30"));

        onView(withId(android.R.id.button1)).perform(click());


        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        List<Ticket> expected = getTicketsByCategoryId(1);

        expected.add(getTestTicket());

        ListTester lt = new ListTester();

        assertTrue(lt.listsEqual(actual, expected));
    }

    @Test
    public void gui_addDataDialog_showsTitleErrorToast() {
        onView(withId(R.id.btn_addticket)).perform(click());

        onView(withId(android.R.id.button1)).perform(click());

        onView(withText(Configuration.YOU_HAVE_TO_ENTER_A_TITLE)).
                inRoot(withDecorView(not(is(myActivityRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void gui_addDataDialog_showsStartErrorToast() {
        onView(withId(R.id.btn_addticket)).perform(click());

        onView(withId(R.id.et_title)).perform(typeText("Test Ticket"));
        onView(withId(R.id.et_start)).perform(typeText("asdf"));
        onView(withId(R.id.et_end)).perform(typeText("12.12.2020 15:30"));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withText(Configuration.THE_GIVEN_START_DATE_TIME_COULD_NOT_BE_PARSED)).
                inRoot(withDecorView(not(is(myActivityRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void gui_addDataDialog_showsEndErrorToast() {
        onView(withId(R.id.btn_addticket)).perform(click());

        onView(withId(R.id.et_title)).perform(typeText("Test Ticket"));
        onView(withId(R.id.et_start)).perform(typeText("12.12.2020 15:30"));
        onView(withId(R.id.et_end)).perform(typeText("asdf"));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withText(Configuration.THE_GIVEN_END_DATE_TIME_COULD_NOT_BE_PARSED)).
                inRoot(withDecorView(not(is(myActivityRule.getActivity().getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void gui_shiftData_oneToAnother_removed() {
        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);
        assertTrue(underTest.getAdapter().getItem(0) instanceof Ticket);
        Ticket shiftedTicket = (Ticket) underTest.getAdapter().getItem(0);

        List<Ticket> ticketListWithRemovedShiftTicket = removeTicketFromList(getTicketsByCategoryId(1), shiftedTicket);

        onData(anything()).inAdapterView(withId(R.id.lv_mainListView)).atPosition(0).perform(longClick());

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }


        ListTester<Ticket> lt = new ListTester<>();

        assertTrue(lt.listsEqual(ticketListWithRemovedShiftTicket, actual));
    }

    @Test
    public void gui_shiftData_oneToAnother_addedToTheOtherList() {
        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);
        assertTrue(underTest.getAdapter().getItem(0) instanceof Ticket);
        Ticket shiftedTicket = (Ticket) underTest.getAdapter().getItem(0);

        onData(anything()).inAdapterView(withId(R.id.lv_mainListView)).atPosition(0).perform(longClick());

        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Next")).perform(click());

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        shiftedTicket.setCategory(new Category(2, "Next"));


        ListTester<Ticket> lt = new ListTester<>();

        List<Ticket> expected = getTicketsByCategoryId(2);
        expected.add(shiftedTicket);

        assertTrue(lt.listsEqual(expected, actual));
    }

    @Test
    public void gui_shiftData_lastCategoryFunctionality() {
        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);
        assertTrue(underTest.getAdapter().getItem(0) instanceof Ticket);

        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Done")).perform(click());

        onData(anything()).inAdapterView(withId(R.id.lv_mainListView)).atPosition(0).perform(longClick());

        List<Ticket> actual = new ArrayList<>();

        for(int i = 0; i < underTest.getAdapter().getCount(); i++) {
            assertTrue(underTest.getAdapter().getItem(i) instanceof Ticket);

            actual.add((Ticket) underTest.getAdapter().getItem(i));
        }

        ListTester<Ticket> lt = new ListTester<>();

        List<Ticket> expected = getTicketsByCategoryId(4);

        assertTrue(lt.listsEqual(expected, actual));
    }

    @Test
    public void gui_dataDetails_dialogCorrect() {
        ListView underTest = myActivityRule.getActivity().findViewById(R.id.lv_mainListView);
        assertTrue(underTest.getAdapter().getItem(0) instanceof Ticket);
        Ticket detailTicket = (Ticket) underTest.getAdapter().getItem(0);

        onData(anything()).inAdapterView(withId(R.id.lv_mainListView)).atPosition(0).perform(click());

        onView(withText(detailTicket.getDescription())).check(matches(isDisplayed()));
        onView(withText(detailTicket.getTitle())).check(matches(isDisplayed()));
    }

    @Test
    public void gui_calculate_hours_backlog() {
        onView(withId(R.id.btn_calculateTotalHours)).perform(click());
        onView(withText(String.format("Current category: %d\nTotal hours: %d", 80, 362))).check(matches(isDisplayed()));
    }

    @Test
    public void gui_calculate_hours_next() {
        onView(withId(R.id.sp_categories)).perform(click());
        onView(withText("Next")).perform(click());
        onView(withId(R.id.btn_calculateTotalHours)).perform(click());
        onView(withText(String.format("Current category: %d\nTotal hours: %d", 42, 362))).check(matches(isDisplayed()));
    }

    @Test
    public void gui_save_doesSomething() {
        String oldc = readDataFile();

        onView(withId(R.id.btn_addticket)).perform(click());

        onView(withId(R.id.et_title)).perform(typeText("Test Ticket"));
        onView(withId(R.id.et_description)).perform(typeText("Dies ist eine extensive description des Tickets und wird im Desc-Tag abgespeichert."));
        onView(withId(R.id.et_start)).perform(typeText("12.12.2020 12:30"));
        onView(withId(R.id.et_end)).perform(typeText("12.12.2020 15:30"));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_save)).perform(click());

        String newc = readDataFile();

        assertNotEquals(oldc, newc);
    }

    @Test
    public void gui_save_stores() {
        onView(withId(R.id.btn_addticket)).perform(click());

        onView(withId(R.id.et_title)).perform(typeText("Test Ticket"));
        onView(withId(R.id.et_description)).perform(typeText("Test desc"));
        onView(withId(R.id.et_start)).perform(typeText("12.12.2020 12:30"));
        onView(withId(R.id.et_end)).perform(typeText("12.12.2020 15:30"));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_save)).perform(click());
        onView(withId(R.id.btn_save)).perform(click());

        String newc = readDataFile();

        assertTrue(newc.contains("Test Ticket"));
        assertTrue(newc.contains("Test desc"));
        assertTrue(newc.contains("12.12.2020 12:30"));
        assertTrue(newc.contains("12.12.2020 15:30"));
    }


    private List<Ticket> removeTicketFromList(List<Ticket> lst, Ticket ticketToRemove) {
        List<Ticket> result = new ArrayList<>();

        ListTester<Ticket> lt = new ListTester<>();

        for(Ticket t : lst) {
            if(!lt.ticketEquals(t, ticketToRemove)) {
                result.add(t);
            }
        }

        return result;
    }

    public Ticket getTestTicket() {
        Category c0 = new Category(1, "Backlog");
        LocalDateTime s0 = LocalDateTime.parse("2020-12-12T12:30");
        LocalDateTime e0 = LocalDateTime.parse("2020-12-12T15:30");
        Ticket t0 = new Ticket("Test Ticket", "Dies ist eine extensive description des Tickets und wird im Desc-Tag abgespeichert.", s0, e0, c0);

        return t0;
    }

    private class ListTester<T> {
        private boolean listsEqual(List<T> listA, List<T> listB) {
            for (T cA : listA) {
                if (!isElementInList(cA, listB))
                    return false;
            }

            for (T cB : listB) {
                if (!isElementInList(cB, listA))
                    return false;
            }

            return true;
        }

        private boolean isElementInList(T needle, List<T> list) {
            for (T cElement : list) {
                if (cElement instanceof Category) {
                    if (categoryEquals((Category) cElement, (Category) needle))
                        return true;
                }
                if (cElement instanceof Ticket) {
                    if (ticketEquals((Ticket) cElement, (Ticket) needle)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean categoryEquals(Category a, Category b) {
            return a.getId() == b.getId() && a.getName().equals(b.getName());
        }

        private boolean ticketEquals(Ticket a, Ticket b) {
            return a.getTitle().equals(b.getTitle()) &&
                    a.getDescription().equals(b.getDescription()) &&
                    categoryEquals(a.getCategory(), b.getCategory()) &&
                    a.getStart().toString().equals(b.getStart().toString()) &&
                    a.getEnd().toString().equals(b.getEnd().toString());
        }
    }

    private Category getCategoryById(int i) {
        for(Category cCategory : getAllCategories()) {
            if(cCategory.getId() == i)
                return cCategory;
        }

        return null;
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

    public List<Ticket> getTicketsByCategoryId(int id) {
        List<Ticket> result = new ArrayList<>();

        for(Ticket cTicket : getAllTickets()) {
            if(cTicket.getCategory().getId() == id)
                result.add(cTicket);
        }

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

    private File deleteFileIfExists() {
        File file = new File(myActivityRule.getActivity().getFilesDir(), Configuration.DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
        return file;
    }

    public String readDataFile() {
        File file = new File(myActivityRule.getActivity().getFilesDir(), Configuration.DATA_FILE);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            return new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void resetFile() {
        deleteFileIfExists();
        FileWriter fw = null;
        try {
            fw = new FileWriter(new File(myActivityRule.getActivity().getFilesDir(), Configuration.DATA_FILE));
            fw.write(dbFileContent);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private final String dbFileContent = "<dataset>\n" +
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
}
