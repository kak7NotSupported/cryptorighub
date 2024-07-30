package me.dyk4lis;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CryptoBot extends TelegramLongPollingBot {
    ConfigManager configManager = ConfigManager.getInstance();
    ConfigDTO.Locale locale = configManager.getConfigDTO().getLocale();


    @Override
    public String getBotUsername() {
        return "null";
    }

    @Override
    public String getBotToken() {
        return ConfigManager.getInstance().getConfigDTO().getConfig().getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        handleMessage(update);
    }


    private void handleMessage(Update update) {
        Message message = update.getMessage();
        String messageText = message.getText();
        String responseText = "";

        if (messageText.equals(locale.getPriceButtonText())) {
            responseText = locale.getPriceText();
        } else if (messageText.equals(locale.getMakeAnOrderButtonText())) {
            responseText = locale.getMakeAnOrderText();
        } else if (messageText.equals(locale.getCurrentAvailabilityButtonText())) {
            responseText = locale.getCurrentAvailabilityText();
        } else {
            responseText = "Неизвестная команда!";
        }


//        String responseText = switch (message.getText()) {
//            case locale.getPriceButtonButtonText() -> ;
//            case "Сделать заказ" -> configManager.getConfigDTO().getLocale().getMakeAnOrderButtonText();
//            case "Актуальное наличие" -> configManager.getConfigDTO().getLocale().getCurrentAvailabilityButtonText();
//            default -> "Неизвестная команда!";
//        };


        SendMessage sendMessage = new SendMessage();


        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(responseText);

        sendMessage.setReplyMarkup(KeyboardManager.getInstance().createKeyBoardMarkup(new String[][]{
                {locale.getPriceButtonText(), locale.getMakeAnOrderButtonText()},
                {locale.getCurrentAvailabilityButtonText()},

        }));

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}















