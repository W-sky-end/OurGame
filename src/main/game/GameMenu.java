package main.game;

import main.Messages;
import main.Player.Player;
import main.battle.Battle;
import main.items.HealthPotion;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class GameMenu extends TelegramLongPollingBot {
    private final String BOT_NAME = "MGT0304";
    private static final String BOT_TOKEN = "7737184129:AAGIWPh9gbC5eWeDuDrt_OboyfxZecQmlUI";

    private final Battle battle = new Battle();
    private final Player player = new Player();
    private States currentState = States.NEW;


    public static void main(String[] args) throws TelegramApiException {

        var bot = new TelegramBotsApi(DefaultBotSession.class);
        bot.registerBot(new GameMenu(BOT_TOKEN));
        ;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String tgAnswer = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            handleInput(tgAnswer, chatId);
        }
    }

    private void handleInput(String input, long chatId) {
        switch (currentState) {
            case NEW -> returnToMainMenu(chatId);
            case MAIN_MENU -> handleMainMenu(input, chatId);
            case SETTINGS -> handleSettings(input, chatId);
            case IN_GAME -> handleGameInput(input, chatId);
            case BACKPACK -> handleBackpackInput(input, chatId);
        }
    }

    private void returnToMainMenu(long chatId) {
        sendMessage(new SendMessage(String.valueOf(chatId), Messages.mainMenuText()));
        currentState = States.MAIN_MENU;
    }

    private void handleMainMenu(String input, long chatId) {
        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1 -> {
                    currentState = States.IN_GAME;
                    String battleMessage = battle.startBattle(player);
                    sendPhoto(battle.getCurrentMonster().getImage(), chatId);
                    sendMessage(new SendMessage(String.valueOf(chatId), battleMessage));
                }
                case 2 -> {
                    sendMessage(new SendMessage(String.valueOf(chatId), "Настройки пока недоступны."));
                    returnToMainMenu(chatId);
                }
                case 3 -> {
                    currentState = States.BACKPACK;
                    sendMessage(new SendMessage(String.valueOf(chatId), player.showBackpack()));
                    sendMessage(new SendMessage(String.valueOf(chatId), """
                        Что вы хотите сделать?
                        1. Добавить предмет
                        2. Использовать предмет
                        0. Вернуться в меню
                        """));
                }
                default -> sendMessage(new SendMessage(String.valueOf(chatId), Messages.nonExistsNumber()));
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.invalidInputText()));
        }
    }

    private void handleSettings(String input, long chatId) {
        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 0 -> {
                    sendMessage(new SendMessage(String.valueOf(chatId), "Back to menu."));
                    returnToMainMenu(chatId);
                }
               // case 1 -> sendMessage(new SendMessage(String.valueOf(chatId), "Empty choice."));
                default -> sendMessage(new SendMessage(String.valueOf(chatId), Messages.nonExistsNumber()));
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.invalidInputText()));
        }
    }

    private void handleGameInput(String input, long chatId) {
        try {
            int choice = Integer.parseInt(input);

            sendMessage(new SendMessage(String.valueOf(chatId), battle.processBattleInput(choice, player)));
            if (!battle.isBattleRunning()) {
                sendMessage(new SendMessage(String.valueOf(chatId), "Бой завершён. Возвращаемся в главное меню."));
                returnToMainMenu(chatId);
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.invalidInputText()));
        }
    }

    private void handleBackpackInput(String input, long chatId) {
        switch (input) {
            case "1" -> {
                player.addItem(new HealthPotion());
                sendMessage(new SendMessage(String.valueOf(chatId), "Вы получили Зелье здоровья!"));
            }
            case "2" -> {
                if (player.useItem(1)) {
                    player.setHealth(player.getHealth() + 20);
                    sendMessage(new SendMessage(String.valueOf(chatId),
                            "Вы использовали Зелье здоровья! Текущее здоровье: " + player.getHealth()));
                } else {
                    sendMessage(new SendMessage(String.valueOf(chatId), "У вас нет Зелий здоровья."));
                }
            }
            case "0" -> returnToMainMenu(chatId);
            default -> sendMessage(new SendMessage(String.valueOf(chatId), Messages.nonExistsNumber()));
        }
    }

    void sendPhoto(String photoPath, long chatId) {

        var photo = getClass().getClassLoader().getResourceAsStream(photoPath);
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, "photoName"));

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    private void sendMessage(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public GameMenu(String botToken) {
        super(botToken);
    }
}

