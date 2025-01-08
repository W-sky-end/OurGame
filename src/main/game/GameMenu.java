package main.game;

import main.Messages;
import main.battle.Battle;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

public class GameMenu extends TelegramLongPollingBot {
    private final String BOT_NAME = "";
    private static final String BOT_TOKEN = "";

    private final Battle battle = new Battle();
    private GameState currentState = GameState.NEW;

    private enum GameState {
        MAIN_MENU,
        SETTINGS,
        IN_GAME,
        NEW

    }

    public static void main(String[] args) throws TelegramApiException {

        var bot = new TelegramBotsApi(DefaultBotSession.class);
        bot.registerBot(new GameMenu(BOT_TOKEN));;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String tgAnswer = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            handleInput(tgAnswer, chatId);
        }
    }

    private void sendMessage(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleInput(String input, long chatId) {
        switch (currentState) {
            case NEW -> returnToMainMenu(chatId);
            case MAIN_MENU -> handleMainMenu(input, chatId);
            case SETTINGS -> handleSettings(input, chatId);
            case IN_GAME -> handleGameInput(input, chatId);
        }
    }

    private void returnToMainMenu(long chatId) {
        sendMessage(new SendMessage(String.valueOf(chatId), Messages.getMainMenuText()));
        currentState = GameState.MAIN_MENU;
    }

    private void handleMainMenu(String input, long chatId) {
        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1 -> {
                    sendMessage(new SendMessage(String.valueOf(chatId), "Игра начинается!"));
                    currentState = GameState.IN_GAME;
                    sendMessage(new SendMessage(String.valueOf(chatId), battle.startBattle()));

                }
                case 2 -> {
                    sendMessage(new SendMessage(String.valueOf(chatId), Messages.getSettingsText()));
                    currentState = GameState.SETTINGS;
                }
                case 3 -> {
                    sendMessage(new SendMessage(String.valueOf(chatId), "Выход из игры. До встречи!"));
                    returnToMainMenu(chatId);
                }
                default -> sendMessage(new SendMessage(String.valueOf(chatId), Messages.getNonExistsNumber()));

            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.getInvalidInputText()));
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
                case 1 -> sendMessage(new SendMessage(String.valueOf(chatId), "Empty choice."));
                default -> sendMessage(new SendMessage(String.valueOf(chatId), Messages.getNonExistsNumber()));
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.getInvalidInputText()));
        }
    }

    private void handleGameInput(String input, long chatId) {
        try {
            int choice = Integer.parseInt(input);

            sendMessage(new SendMessage(String.valueOf(chatId),  battle.processBattleInput(choice)));
            if (!battle.isBattleRunning()) {
                // defeat lost ran away
                sendMessage(new SendMessage(String.valueOf(chatId), "Бой завершён. Возвращаемся в главное меню."));
                returnToMainMenu(chatId);
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.getInvalidInputText()));
        }
    }

    void sendPhoto(long chatId) throws Exception {
        var photo = getClass().getClassLoader().getResourceAsStream("2.jpg");
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, "photo"));

        execute(message);
        }


        @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public GameMenu(String botToken) {
        super(botToken);
    }
}

