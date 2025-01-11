package main.game;

import main.Messages;
import main.Player.Player;
import main.battle.Battle;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class GameMenu extends TelegramLongPollingBot {
    private final String BOT_NAME = "MGT0304_bot";
    private static final String BOT_TOKEN = "7737184129:AAGIWPh9gbC5eWeDuDrt_OboyfxZecQmlUI";

    private final Battle battle = new Battle();
    private final Player player = new Player();  // <____________Добавил игрока
    private GameState currentState = GameState.NEW;

    private enum GameState {
        MAIN_MENU,
        SETTINGS,
        IN_GAME,
        BAGPACK,  // <_______ Сумка
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
            case BAGPACK -> handleBagpackInput(input, chatId); // +++++
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

                    String battleMessage = battle.startBattle(player);
                    sendMessage(new SendMessage(String.valueOf(chatId), battleMessage));
                }
                case 2 -> {
                    sendMessage(new SendMessage(String.valueOf(chatId), "Настройки пока недоступны."));
                    currentState = GameState.SETTINGS;
                }
                default -> sendMessage(new SendMessage(String.valueOf(chatId), "Выберите один из предложенных вариантов."));
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), "Пожалуйста, введите число."));
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

            sendMessage(new SendMessage(String.valueOf(chatId),  battle.processBattleInput(choice))); // заменена послядняя часть
            if (!battle.isBattleRunning()) {
                // defeat lost ran away
                sendMessage(new SendMessage(String.valueOf(chatId), "Бой завершён. Возвращаемся в главное меню."));
                returnToMainMenu(chatId);
            }
        } catch (NumberFormatException e) {
            sendMessage(new SendMessage(String.valueOf(chatId), Messages.getInvalidInputText()));
        }
    }
    private void handleBagpackInput(String input, long chatId) {    // <-----+++++++++++
        switch (input){
            case "1" -> {
                player.addItem("Зелье хп");
                sendMessage(new SendMessage(String.valueOf(chatId),"Дропнуло зелье в сумку"));
            }
            case "2" -> {
                if(player.removeItem("Зелье хп")){
                    sendMessage(new SendMessage(String.valueOf(chatId),"Ты использовал зелье"));
                }else{
                    sendMessage(new SendMessage(String.valueOf(chatId), "Нет зелек, база!"));
                }
            }
            case "0" -> returnToMainMenu(chatId);
            default -> sendMessage(new SendMessage(String.valueOf(chatId), Messages.getNonExistsNumber()));
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

