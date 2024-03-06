package com.qacart.todo.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    private static ConfigUtils configUtils;
    private Properties properties;
    private ConfigUtils() {
        properties = getProperties();
    }
//getInstance
    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    private Properties getProperties() {
        String env = System.getProperty("env", "prod");
        String file;
        switch (env) {
            case "prod" -> file = "prod.properties";
            case "qa" -> file = "qa.properties";
            case "dev" -> file = "dev.properties";
            case "staging" -> file = "staging.properties";
            case "uat" -> file = "uat.properties";
            case "local" -> file = "local.properties";
            default -> throw new RuntimeException("Unexpected value env not supported: " + env);
        }
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/env/"+file)) {
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return properties;
    }

    public String getBaseUrl() {
        return properties.get("URL").toString();
    }

}
