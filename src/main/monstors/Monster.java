package main.monstors;

import main.items.Item;
import main.items.Weapon;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster {

    private final int maxHealth;
    private String name;
    private int health;
    private int damage;
    private String image;
    private int experienceReward;
    private List<Weapon> weaponPossibleDrop;


    public Monster(String name, int health, int damage, String image, int experienceReward,List<Weapon>weaponPossibleDrop) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.image = image;
        this.maxHealth = health;
        this.experienceReward = experienceReward;
        this.weaponPossibleDrop = new ArrayList<>(weaponPossibleDrop);
    }
    public Weapon dropWeapon() {
        Random random = new Random();
        if (weaponPossibleDrop.isEmpty() || random.nextInt(100) >= 50) { // 50% шанс дропа
            return null;
        }
        int randomIndex = random.nextInt(weaponPossibleDrop.size());
        return weaponPossibleDrop.get(randomIndex);
    }

    public void resetHealth() {
        this.health = this.maxHealth;
    }

    public String getName() {
        return name;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public String getImage() {
        return image;
    }


    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", image='" + image + '\'' +
                '}';
    }

}
