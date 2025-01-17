package main.monstors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonstersStack {
    private final List<Monster> monsters = new ArrayList<>();
    private final Random random = new Random();

    public MonstersStack() {
        registerMonsters();
    }


    public void registerMonsters() {
        monsters.add(new Monster("Imp", 35, 5, "images/bes.jpg"));
        monsters.add(new Monster("Height Imp", 55, 7, "images/heightBes.jpg"));
        monsters.add(new Monster("Red Boss", 100, 15, "images/Red Boss.jpg"));
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public boolean removeMonster(Monster monster) {
        return monsters.remove(monster);
    }


    public boolean isEmpty() {
        return monsters.isEmpty();
    }

    public Monster pickMonster() {
        int totalWeight = monsters.size();
        return monsters.get(random.nextInt(totalWeight));
    }
}

