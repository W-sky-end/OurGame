package main.monstors;

import main.items.Weapon;

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
        List<Weapon> impsDrop = List.of(
                new Weapon(1, "Ложка Евы", "Обычная ложка Евы, кажется она ей ест.", 1),
                new Weapon(2, "Палка", "Обычная палка", 2),
                new Weapon(3, "Камень", "Обычный камень", 3),
                new Weapon(5, "Ножик", "Простой ножик.", 5)
        );

        List<Weapon> fstBossDrop = List.of(
                new Weapon(4, "Кинжал Богдана", "В далеком 2006, он был смертоносным.", 4),
                new Weapon(6, "Кусок НЛО", "Дед Максим его поднял в далеком детстве" +
                        " или его облучило,или Леонардо из черепашек Ниндзя существует.", 6),
                new Weapon(5, "Навали-наваля", "Тут без комментариев.", 100)
        );


        monsters.add(new Monster("Imp", 35, 5, "images/bes.jpg", 5, impsDrop));
        monsters.add(new Monster("Height Imp", 55, 7, "images/heightBes.jpg", 7, impsDrop));
        monsters.add(new Monster("Red Boss", 100, 15, "images/Red Boss.jpg", 100, fstBossDrop));
    }

    public boolean isEmpty() {
        return monsters.isEmpty();
    }

    public Monster pickMonster() {
        int totalWeight = monsters.size();
        return monsters.get(random.nextInt(totalWeight));
    }
}
