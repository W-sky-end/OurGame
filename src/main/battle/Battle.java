package main.battle;

import main.Messages;
import main.game.GameMenu;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private boolean battleRunning = true;
    int playerHealth = 100;
    int enemyHealth = 50;


    public boolean isBattleRunning() {

        return battleRunning;
    }


    public String startBattle() {
        return Messages.getInGameHudStart(playerHealth, enemyHealth);
    }


    public String processBattleInput(int choice) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        switch (choice) {
            case 1 -> {
                int playerDamage = random.nextInt(10) + 5;
                int enemyDamage = random.nextInt(10) + 3;

                enemyHealth -= playerDamage;
                playerHealth -= enemyDamage;

                result.append(Messages.getInGameBattleResult(playerDamage, enemyDamage))
                        .append("\n")
                        .append(Messages.getInGameHud(playerHealth, enemyHealth));

                if (enemyHealth <= 0) {
                    result = new StringBuilder("Вы победили врага!");
                    resetBattle();
                } else if (playerHealth <= 0) {
                    result = new StringBuilder("Вы погибли...");
                    resetBattle();
                }
            }
            case 2 -> {
                result = new StringBuilder("Вы сбежали от врага!");
                resetBattle();
            }
            default -> {
                result = new StringBuilder(Messages.getNonExistsNumber());
            }
        }
        return result.toString();
    }

    public void resetBattle() {
        playerHealth = 100;
        enemyHealth = 50;
        battleRunning = true;
    }
}