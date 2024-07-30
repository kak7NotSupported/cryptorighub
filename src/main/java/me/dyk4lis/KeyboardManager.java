package me.dyk4lis;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardManager {
    private static KeyboardManager instance;

    private KeyboardManager() {
    }

    public static KeyboardManager getInstance() {
        if (instance == null) {
            instance = new KeyboardManager();
        }
        return instance;
    }


    public ReplyKeyboardMarkup createKeyBoardMarkup(String[][] arrays) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

//      replyKeyboardMarkup.setInputFieldPlaceholder(text);
        replyKeyboardMarkup.setKeyboard(createKeyboard(arrays));
        return replyKeyboardMarkup;
    }

    public List<KeyboardRow> createKeyboard(String[][] arrays) {
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        for (String[] row : arrays) {
            var keyboardRow = new KeyboardRow();
            for (String buttonText : row) {
                keyboardRow.add(buttonText);

            }
            keyboardRows.add(keyboardRow);

        }
        return keyboardRows;
    }
}
