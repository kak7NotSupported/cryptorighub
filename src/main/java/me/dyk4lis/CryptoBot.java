package me.dyk4lis;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
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
        String responseText;
        SendMessage sendMessage = new SendMessage();



        if (messageText.equals(locale.getPriceButtonText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());

            responseText = locale.getPriceText();
        } else if (messageText.equals(locale.getMakeAnOrderButtonText())) {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = locale.getMakeAnOrderText();
        } else if (messageText.equals(locale.getCurrentAvailabilityButtonText())) {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = locale.getCurrentAvailabilityText();
        }
         else if (messageText.equals(locale.getThePriceIsOnOrderButtonText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText = locale.getThePriceIsOnOrderButtonText();
        }

          else if (messageText.equals(locale.getMakeAnOrderText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText = locale.getMakeAnOrderText();
        }

          else if (messageText.equals(locale.getStockAvailabilityButtonText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText = locale.getStockAvailabilityButtonText();
        }

          else if (messageText.equals(locale.getMenuText())) {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = "Вы вернулись на главную";
        }








        else {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = "Неизвестная команда!";
        }

        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(responseText);


//        sendMessage.setReplyMarkup(KeyboardManager.getInstance().createKeyBoardMarkup(new String[][]{
//                {locale.getThePriceIsOnOrder(),locale.getPriceButtonText()},
//                {locale.getStockAvailability(),locale.getMenu()},
//
//        }));


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getMainKeyBoardMarkup() {
        return KeyboardManager.getInstance().createKeyBoardMarkup(new String[][]{
                {locale.getPriceButtonText(), locale.getMakeAnOrderButtonText()},
                {locale.getCurrentAvailabilityButtonText()},

        });
    }

    private ReplyKeyboardMarkup getSecondKeyboardMarkup() {
        return KeyboardManager.getInstance().createKeyBoardMarkup(new String[][]{
                {locale.getThePriceIsOnOrderButtonText(), locale.getPriceButtonText()},
                {locale.getStockAvailabilityButtonText(), locale.getMenuButtonText()},

        });
    }
}















