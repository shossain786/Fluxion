package com.fluxion.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {

    private static Map<String, String> config;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        // Explicitly use SnakeYAML's Yaml class
        Yaml yaml = new org.yaml.snakeyaml.Yaml();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream("config/application.yaml")) {
            if (inputStream == null) {
                throw new RuntimeException("Configuration file not found");
            }
            config = yaml.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getProperty(String key) {
        return config.get(key);
    }
}
