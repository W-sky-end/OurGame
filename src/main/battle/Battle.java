package main.battle;

import main.Messages;
import main.game.GameMenu;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.Random;
import java.util.Scanner;

public class Battle{
    private boolean battleRunning = true;
    int playerHealth = 100;
    int enemyHealth = 50;


    public boolean isBattleRunning() {
        return battleRunning;
    }


    public String startBattle() {
        resetBattle();
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
                    battleRunning = false;
                } else if (playerHealth <= 0) {
                    result = new StringBuilder("Вы погибли...");
                    battleRunning = false;
                }
            }
            case 2 -> {
                result = new StringBuilder("Вы сбежали от врага!");
                battleRunning = false;
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