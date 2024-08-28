package me.dyk4lis;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CryptoBot extends TelegramLongPollingBot {

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
        MessageHandler messageHandler = new MessageHandler(this);
        messageHandler.handleMessage(update);

        Message message = update.getMessage();  // Получаем объект Message
        String messageText = message.getText(); // Получаем текст сообщения
        long chatId = message.getChatId();      // Получаем ID чата
        System.out.println(chatId);



        // Создаем сообщение для отправки в ответ
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Вы написали: " + messageText);
    }



}















