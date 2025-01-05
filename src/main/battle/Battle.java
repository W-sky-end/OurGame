package main.battle;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    public void startBattle() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int playerHealth = 100;
        int enemyHealth = 50;
        boolean running = true;

        System.out.println("Вы встретили врага!");

        while (running) {
            System.out.println("\nВаше здоровье: " + playerHealth);
            System.out.println("Здоровье врага: " + enemyHealth);
            System.out.println("1. Атаковать");
            System.out.println("2. Убежать");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    int playerDamage = random.nextInt(10) + 5;
                    int enemyDamage = random.nextInt(10) + 3;
                    enemyHealth -= playerDamage;
                    playerHealth -= enemyDamage;

                    System.out.println("Вы нанесли врагу " + playerDamage + " урона.");
                    System.out.println("Враг атаковал вас и нанёс " + enemyDamage + " урона.");

                    if (enemyHealth <= 0) {
                        System.out.println("Вы победили врага!");
                        running = false;
                    } else if (playerHealth <= 0) {
                        System.out.println("Вы погибли...");
                        running = false;
                    }
                }
                case 2 -> {
                    System.out.println("Вы сбежали от врага!");
                    running = false;
                }
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}