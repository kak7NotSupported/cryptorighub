package me.dyk4lis;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;


public class MessageHandler {
    ConfigManager configManager = ConfigManager.getInstance();
    ConfigDTO.Locale locale = configManager.getConfigDTO().getLocale();
    CryptoBot cryptoBot;
    Database database = Database.getInstance();

    public MessageHandler(CryptoBot cryptoBot) {
        this.cryptoBot = cryptoBot;
    }

    public void handleMessage(Update update) {
        Message message = update.getMessage();
        String messageText = message.getText();
        String responseText;
        SendMessage sendMessage = new SendMessage();
        User user = message.getFrom();


        database.updateLastIterationTime(user.getId(), user.getUserName());


        if (messageText.equals(locale.getPriceButtonText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());

            responseText = locale.getPriceText();
        } else if (messageText.equals(locale.getMakeAnOrderButtonText())) {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = locale.getMakeAnOrderText();
        } else if (messageText.equals(locale.getCurrentAvailabilityButtonText())) {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = locale.getCurrentAvailabilityText();
        } else if (messageText.equals(locale.getThePriceIsOnOrderButtonText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText = locale.getThePriceIsOnOrderButtonText();
        } else if (messageText.equals(locale.getMakeAnOrderText())) {
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText = locale.getMakeAnOrderText();
        } else if (messageText.equals(locale.getStockAvailabilityButtonText())) {
            sendDocument(message.getChatId(), "");
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText = locale.getStockAvailabilityButtonText();
        } else if (messageText.equals(locale.getMenuText())) {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = "Вы вернулись на главную";

        } else if (messageText.equals(locale.getThePricePreOrderButtonText())) {  // кнопка прайс: предзаказ
            sendMessage.setReplyMarkup(getSecondKeyboardMarkup());
            responseText =locale.getThePriceIsOnOrderText();
            sendDocument(message.getChatId(), "");

        } else {
            sendMessage.setReplyMarkup(getMainKeyBoardMarkup());
            responseText = "Неизвестная команда!";
        }

        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(responseText);


        try {
            var message1 = cryptoBot.execute(sendMessage);


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
                {locale.getThePriceIsOnOrderButtonText(), locale.getThePricePreOrderButtonText()},
                {locale.getStockAvailabilityButtonText(), locale.getMenuButtonText()},

        });
    }


    public void sendDocument(long chatId, String fileName) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(new File("C:\\Users\\dyk4lis\\Documents\\9001123119_A.pdf")));


        try {
            cryptoBot.execute(sendDocument); // Отправка документа
        } catch (TelegramApiException e) {
            e.printStackTrace(); // Обработка ошибок
        }

    }

}
