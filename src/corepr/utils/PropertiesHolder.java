package corepr.utils;

import java.io.IOException;
import java.util.Properties;


public class PropertiesHolder {

    private static Properties properties;

    static {

        properties = new Properties();
        try {
            properties.load(PropertiesHolder.class.getResourceAsStream("/resources/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }

}
