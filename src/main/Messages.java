package main;

public class Messages {
    public static String mainMenuText(){
        return " === Главное меню === " +
                " \n1. Начать игру" +
                " \n2. Настройки" +
              //  " \n3. Выйти" +
                " \nВыберите действие: ";
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

    public static String inGameHud(int playerHealth, int enemyHealth){
        return "Ваше здоровье: " + playerHealth +
                "\n Здоровье врага: " + enemyHealth +
                "\n1. Атаковать" +
                "\n2. Убежать" +
                "\nВаш выбор:";
    }
    public static String inGameHudStart(int playerHealth, int enemyHealth){
        return "Вы встретили врага!\n" + inGameHud(playerHealth, enemyHealth);
    }
    public static String inGameBattleResult(int playerDamage, int enemyDamage){
        return "Вы нанесли врагу " + playerDamage + " урона." +
                "\nВраг атаковал вас и нанёс " + enemyDamage + " урона.";
    }
}
