package at.htlgkr.ticketingsystem;

public class Category {
    private int id;
    private String name;

    // do not change this constructor !!
    public Category(int id, String name) {
        this.id = id;
        name = name.replaceAll("\"", "");
        name = name.replaceAll(" ", "");
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String temp = name.replaceAll("\"", "");
        return temp;
    }
}
