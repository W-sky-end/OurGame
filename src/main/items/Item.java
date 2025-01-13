package main.items;

public class Item {
    private final int id;
    private final String name;
    private final String description;


    public Item(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean equals(Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id ;
    }
    @Override
    public int hashCode() {
        return id;
    }
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
    }

