package me.dyk4lis;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.URISyntaxException;


public class Main {

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        CryptoBot bot = new CryptoBot();
        botsApi.registerBot(bot);

        try {
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }





    }
}











