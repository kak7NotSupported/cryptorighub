package me.dyk4lis;

import org.telegram.telegrambots.meta.api.objects.File;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static Database instance;

    private Database() {

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static void main(String[] args) {
        Database db = Database.getInstance();

    }


    public void updateLastIterationTime(long telegramId,String username) {




        // URL базы данных, имя пользователя и пароль
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "12344321";

        // Запрос на вставку данных
        String SQL_INSERT = """
                INSERT INTO crh.users (telegram_id,user_name)
                VALUES (?,?)
                ON CONFLICT (telegram_id)
                DO UPDATE SET  last_interaction = now()
                """;


        // Подключение к базе данных
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            // Устанавливаем значения параметров для вставки
            preparedStatement.setLong(1, telegramId);
            preparedStatement.setString(2, username);

            // Выполняем запрос
            int row = preparedStatement.executeUpdate();

            // Проверяем, вставлены ли данные
            if (row > 0) {
                System.out.println("Данные успешно вставлены!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








}







