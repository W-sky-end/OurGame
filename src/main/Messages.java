package main;

public class Messages {
    public static String mainMenuText() {
        return """
           === Главное меню ===
                1. Начать игру
                2. Настройки
                3. Инвентарь
                4. Информация о персонаже
                Выберите действие:
                """;
    }

    public static String settingsText() {
        return """
                Здесь будут настройки...
                0. Выйти в меню.
                1. void.
               """;
    }

    public static String nonExistsNumber() {
        return "Некорректный выбор, попробуйте снова.";
    }

    public static String invalidInputText(){
        return  "Вводите только цифры!";
    }

    public static String inGameHud(String playerHealth, int enemyHealth){
        return "Ваше здоровье: " + playerHealth +
                "\n Здоровье врага: " + enemyHealth +
                "\n1. Атаковать" +
                "\n2. Убежать" +
                "\n3. Использовать зелье здоровья" +
                "\nВаш выбор:";
    }
    public static String inGameHudStart(int playerHealth, int enemyHealth){
        return "Вы встретили врага!\n" + inGameHud(String.valueOf(playerHealth), enemyHealth);
    }
    public static String inGameBattleResult(int playerDamage, int enemyDamage){
        return "Вы нанесли врагу " + playerDamage + " урона." +
                "\nВраг атаковал вас и нанёс " + enemyDamage + " урона.";
    }
}
