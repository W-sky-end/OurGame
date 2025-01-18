package main.Player;

import main.backpack.Backpack;
import main.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private static int health = 100;
    private static int maxHealth = 100;
    private final Backpack backpack = new Backpack();
    private int level = 1;
    private int experience = 0;
    private int experienceToNextLevel = 100;

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
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

    public static String getHealthStatus() {
        return health + "/" + maxHealth;
    }

    public void addExperience(int amount) {
        experience += amount;
        while (experience >= experienceToNextLevel) {
            levelUp();
        }
    }
    private void levelUp() {
        level++;
        experience -= experienceToNextLevel;
        maxHealth += 10;
        health = maxHealth;
        System.out.println("Поздравляю нюбик у тебя lvlUP " + level + "!");
    }

    private int getExperienceForNextLevel() {
        return level * 100;
    }
    public String getCharacterInfo() {
        return "=== Информация о персонаже ===\n" +
                "Уровень: " + level + "\n" +
                "Опыт: " + experience + "/" + getExperienceForNextLevel() + "\n" +
                "Здоровье: " + getHealthStatus();
    }
    private boolean levelUpNotification = false;

    public boolean hasLevelUpNotification() {
        return levelUpNotification;
    }

    public void clearLevelUpNotification() {
        levelUpNotification = false;
    }
}


