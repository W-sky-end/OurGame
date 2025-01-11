package main.battle;

import main.Messages;
import main.Player.Player;
import main.monstors.Monster;
import main.monstors.MonstersStack;

import java.util.Random;

public class Battle {
    private boolean battleRunning = true;
    private final MonstersStack monstersStack = new MonstersStack();
    private Monster currentMonster;

    public boolean isBattleRunning() {
        return battleRunning;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public String startBattle(Player player) {
        if (monstersStack.isEmpty()) {
            return "Нет доступных монстров для битвы!";
        }

        currentMonster = monstersStack.pickMonster();
        System.out.println("Выбран монстр: " + currentMonster.getName());

        player.setHealth(100);
        currentMonster.setHealth(currentMonster.getHealth());
        battleRunning = true;
        return "Битва началась! Монстр: " + currentMonster.getName();
    }


    public String processBattleInput(int choice) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        switch (choice) {
            case 1 -> {
                int playerDamage = random.nextInt(10) + 5;
                int monsterDamage = random.nextInt(10) + 3;

                currentMonster.setHealth(Math.max(0, currentMonster.getHealth() - playerDamage));
                Player.setHealth(Math.max(0, Player.getHealth() - monsterDamage));

                result.append(Messages.getInGameBattleResult(playerDamage, monsterDamage))
                        .append("\n")
                        .append(Messages.getInGameHud(Player.getHealth(), currentMonster.getHealth()));

                if (currentMonster.getHealth() <= 0) {
                    result = new StringBuilder("Вы победили монстра " + currentMonster.getName() + "!");
                    battleRunning = false;
                } else if (Player.getHealth() <= 0) {
                    result = new StringBuilder("Вы погибли...");
                    battleRunning = false;
                }
            }
            case 2 -> {
                result = new StringBuilder("Вы сбежали от монстра " + currentMonster.getName() + "!");
                battleRunning = false;
            }
            default -> result = new StringBuilder(Messages.getNonExistsNumber());
        }
        return result.toString();
    }
}
