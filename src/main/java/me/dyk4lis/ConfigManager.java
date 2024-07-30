package me.dyk4lis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@JsonIgnoreProperties
public class ConfigManager {
    private static ConfigManager instance;
    @Getter
    private ConfigDTO configDTO;

    private ConfigManager() {
        loadConfig();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadConfig() {
        try {

            configDTO = new ConfigDTO();
            configDTO.setConfig(new ConfigDTO.Config());
            configDTO.setLocale(new ConfigDTO.Locale());

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

            String jsonString = writer.writeValueAsString(configDTO);

            System.out.println(jsonString);

            File file = new File("config.json");
            writer.writeValue(file, configDTO);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
