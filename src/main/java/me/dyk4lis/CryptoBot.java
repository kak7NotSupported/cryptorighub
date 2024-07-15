package me.dyk4lis;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CryptoBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "null";
    }

    @Override
    public String getBotToken() {
        return "7209411504:AAGbtg9WJA_b49LKp99l0spLJW_f8rQQhNY";
    }

    @Override
    public void onUpdateReceived(Update update) {
        String s = update.getMessage().getText();
        System.out.println(s);

    }

}



