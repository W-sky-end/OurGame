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
        if (player.getHealth() <= 0) {
            return "Ваш персонаж слишком слаб, чтобы продолжать сражения. Используйте зелье здоровья!";
        }

        currentMonster = monstersStack.pickMonster();
        System.out.println("Выбран монстр: " + currentMonster.getName());
        currentMonster.resetHealth();

        battleRunning = true;
        return "Битва началась!\nМонстр: " + currentMonster.getName() +
                '\n' + Messages.inGameHud(player.getHealthStatus(), currentMonster.getHealth());
    }

    public String processBattleInput(int choice, Player player) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        switch (choice) {
            case 1 -> {
                int playerDamage = random.nextInt(10) + 5;
                int monsterDamage = random.nextInt(10) + 3;

                currentMonster.setHealth(Math.max(0, currentMonster.getHealth() - playerDamage));
                player.setHealth(Math.max(0, player.getHealth() - monsterDamage));

                result.append(Messages.inGameBattleResult(playerDamage, monsterDamage))
                        .append("\n")
                        .append(Messages.inGameHud(Player.getHealthStatus(), currentMonster.getHealth()));

                if (currentMonster.getHealth() <= 0) {
                    result = new StringBuilder("Вы победили монстра " + currentMonster.getName() + "!");
                    result.append("\nВаше текущее здоровье: ").append(player.getHealthStatus());
                    battleRunning = false;

                    int experienceGained = currentMonster.getExperienceReward();
                    player.addExperience(experienceGained);
                    result.append("\nВы получили ").append(experienceGained).append(" опыта!");

                    if (player.hasLevelUpNotification()) {
                        result.append("\nПоздравляем! Ваш уровень повышен до ").append(player.getLevel()).append("!");
                        player.clearLevelUpNotification();

                    } else if (player.getHealth() <= 0) {
                        result = new StringBuilder("Вы погибли...");
                        battleRunning = false;
                    }
                }
            }
            case 2 -> {
                result = new StringBuilder("Вы сбежали от монстра " + currentMonster.getName() + "!");
                battleRunning = false;
            }
            case 3 -> {
                if (player.useItem(1)) {
                    player.setHealth(player.getHealth() + 20);
                    result.append("Вы использовали зелье здоровья! Текущее здоровье: ").append(player.getHealthStatus());
                } else {
                    result.append("У вас нет зелий здоровья.");
                }
            }
            default -> result = new StringBuilder(Messages.nonExistsNumber());
        }
        return result.toString();
    }
}
