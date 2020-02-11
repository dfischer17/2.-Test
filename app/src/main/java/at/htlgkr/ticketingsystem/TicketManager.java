package at.htlgkr.ticketingsystem;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketManager {
    private List<Category> categories;
    private List<Ticket> tickets = new ArrayList<>();

    // do not change the parameters of this construktor!!!
    public TicketManager(List<Category> categories) {
        // implement this
        this.categories = categories;
    }

    public void addTicket(Ticket newTicket) {
        // implement this
        tickets.add(newTicket);

    }

    public List<Ticket> getTicketList() {
        // implement this
        return tickets;
    }

    public Category getStandardCategory() {
        // implement this
        return categories.get(0);
    }

    public List<Category> getCategories() {
        // implement this
        return categories;
    }

    public void setTicketList(List<Ticket> tickets) {
        // implement this
        this.tickets = tickets;
    }

    public List<Ticket> getTicketListForCategoryId(final int categoryId) {
        // implement this
        return tickets.stream().filter(t -> t.getCategory().getId() == categoryId).collect(Collectors.toList());
    }

    public Category getNextCategory(Category selectedCategory) {
        // implement this
        if (categories.size() == selectedCategory.getId()) {
            return selectedCategory;
        }
        return categories.get(selectedCategory.getId());
    }

    public Category getCategoryById(int categoryId) {
        // implement this
        return categories.get(categoryId - 1);
    }

    public long calcHoursByCategory(Category category) {
        List<Ticket> ticketsByCategory = tickets.stream().filter(t -> t.getCategory().getId() == category.getId()).collect(Collectors.toList());

        long totalHoursPerCategory = 0;

        for (int i = 0; i < ticketsByCategory.size(); i++) {
            totalHoursPerCategory += ChronoUnit.HOURS.between(ticketsByCategory.get(i).getStart(), ticketsByCategory.get(i).getEnd());
        }
        return totalHoursPerCategory;
    }

    public long calcTotalHours() {
        // implement this
        long totalHours = 0;

        for (int i = 0; i < tickets.size(); i++) {
            totalHours += ChronoUnit.HOURS.between(tickets.get(i).getStart(), tickets.get(i).getEnd());
        }
        return totalHours;
    }
}
