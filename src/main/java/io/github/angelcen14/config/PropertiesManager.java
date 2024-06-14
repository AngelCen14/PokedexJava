package io.github.angelcen14.config;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static Properties properties = null;

    public static String getValue(String key) {
        String value = null;
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(PropertiesManager.class.getResourceAsStream("/config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        value = properties.getProperty(key);

        return value;
    }
}
