import java.util.Scanner;

public class GameMenu {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("=== Главное меню ===");
                System.out.println("1. Начать игру");
                System.out.println("2. Настройки");
                System.out.println("3. Выйти");
                System.out.print("Выберите действие: ");

                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> System.out.println("Игра начинается!");
                    case 2 -> System.out.println("Здесь будут настройки...");
                    case 3 -> {
                        System.out.println("Выход из игры. До встречи!");
                        running = false;
                    }
                    default -> System.out.println("Некорректный выбор, попробуйте снова.");
                }
            }
            sc.close();
        }
    }

