package main.battle;


import main.Messages;
import main.Player.Player;
import main.monstors.Monster;
import main.monstors.MonstersStack;



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
        return "Битва началась!\nМонстр: " + currentMonster.getName() + '\n' + Messages.inGameHud(player.getHealth(), currentMonster.getHealth());
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
                        .append(Messages.inGameHud(player.getHealth(), currentMonster.getHealth()));

                if (currentMonster.getHealth() <= 0) {
                    result = new StringBuilder("Вы победили монстра " + currentMonster.getName() + "!");
                    battleRunning = false;
                } else if (player.getHealth() <= 0) {
                    result = new StringBuilder("Вы погибли...");
                    battleRunning = false;
                }
            }
            case 2 -> {
                result = new StringBuilder("Вы сбежали от монстра " + currentMonster.getName() + "!");
                battleRunning = false;
            }
            default -> result = new StringBuilder(Messages.nonExistsNumber());
        }
        return result.toString();
    }

}
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
      
    }
}

