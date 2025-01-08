package main;

public class Messages {
    public static String getMainMenuText(){
        return " === Главное меню === " +
                " \n1. Начать игру" +
                " \n2. Настройки" +
                " \n3. Выйти" +
                " \nВыберите действие: ";
    }

    public static String getSettingsText() {
        return """
                Здесь будут настройки...
                0. Выйти в меню.
                1. void.
               """;
    }

    public static String getNonExistsNumber() {
        return "Некорректный выбор, попробуйте снова.";
    }

    public static String getInvalidInputText(){
        return  "Вводите только цифры!";
    }

    public static String getInGameHud(int playerHealth, int enemyHealth){
        return "Ваше здоровье: " + playerHealth +
                "\n Здоровье врага: " + enemyHealth +
                "\n1. Атаковать" +
                "\n2. Убежать" +
                "\nВаш выбор:";
    }
    public static String getInGameHudStart(int playerHealth, int enemyHealth){
        return "Вы встретили врага!\n" + getInGameHud(playerHealth, enemyHealth);
    }
    public static String getInGameBattleResult(int playerDamage, int enemyDamage){
        return "Вы нанесли врагу " + playerDamage + " урона." +
                "\nВраг атаковал вас и нанёс " + enemyDamage + " урона.";
    }
}
