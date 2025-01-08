package main.battle;

import main.Messages;
import main.Player.Player;

import java.util.Random;

public class Battle {
    private boolean battleRunning = true;
    private int enemyHealth = 50;

    public boolean isBattleRunning() {
        return battleRunning;
    }

    public String startBattle(Player player) {
        resetBattle(player);
        return Messages.getInGameHudStart(player.getHealth(), enemyHealth);
    }

    public String processBattleInput(int choice) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        switch (choice) {
            case 1 -> {
                int playerDamage = random.nextInt(10) + 5;
                int enemyDamage = random.nextInt(10) + 3;

                enemyHealth -= playerDamage;
                player.setHealth(player.getHealth() - enemyDamage); // change

                result.append(Messages.getInGameBattleResult(playerDamage, enemyDamage))
                        .append("\n")
                        .append(Messages.getInGameHud(player.getHealth(), enemyHealth));

                if (enemyHealth <= 0) {
                    result = new StringBuilder("Вы победили врага!");
                    battleRunning = false;
                } else if (player.getHealth() <= 0) {
                    result = new StringBuilder("Вы погибли...");
                    battleRunning = false;
                }
            }
            case 2 -> {
                result = new StringBuilder("Вы сбежали от врага!");
                battleRunning = false;
            }
            case 3 -> {
                if (player.removeItem("Зелье здоровья")) {
                    player.setHealth(player.getHealth() + 20);
                    result = new StringBuilder("Вы использовали 'Зелье здоровья'. Здоровье восстановлено!");
                } else {
                    result = new StringBuilder("У вас нет 'Зелье здоровья'.");
                }
            }
            default -> {
                result = new StringBuilder(Messages.getNonExistsNumber());
            }
        }
        return result.toString();
    }

    public void resetBattle(Player player) {
        player.setHealth(100);
        enemyHealth = 50;
        battleRunning = true;
    }
}