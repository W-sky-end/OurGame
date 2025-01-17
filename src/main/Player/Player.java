package main.Player;

import main.backpack.Backpack;
import main.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private int health = 100;
    private final Backpack backpack = new Backpack();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, 100);
    }

    public void addItem(Item item) {
        backpack.addItem(item);
        System.out.println("Тебе выпало: " + item.getName());
    }
    public boolean useItem(int itemId) {
        return backpack.useItem(itemId);
    }

    public String showBackpack() {
        return backpack.showItems();
    }

    public boolean equipSwordById(int swordId) {
        return false;
    }
}
