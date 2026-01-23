package com.jiji.utils;



import java.io.FileInputStream;
import java.util.Properties;

public final class PropertyUtils {

    private static Properties prop = new Properties();

    static {
        try {
            FileInputStream fis =
                new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    private PropertyUtils() {}

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
