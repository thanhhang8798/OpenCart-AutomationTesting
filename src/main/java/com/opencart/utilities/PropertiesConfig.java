package com.opencart.utilities;

import com.opencart.core.GlobalConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private Properties properties;
    private final String propertyFilePath = GlobalConstants.ENVIRONMENT_CONFIG_PATH + "%s.properties";

    public static PropertiesConfig getProperties(String serverName) {
        return new PropertiesConfig(serverName);
    }

    public PropertiesConfig(String serverName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(String.format(propertyFilePath, serverName)));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties not found at " + propertyFilePath);
        }
    }

    public String getApplicationAdminUrl() {
        String url = properties.getProperty("App.AdminUrl");
        if(url != null) return url;
        else throw new RuntimeException("Url not specified in the properties file.");
    }

    public String getApplicationUserUrl() {
        String url = properties.getProperty("App.UserUrl");
        if(url != null) return url;
        else throw new RuntimeException("Url not specified in the properties file.");
    }

    public String getApplicationUserName() {
        String username = properties.getProperty("App.AdminUsername");
        if(username != null) return username;
        else throw new RuntimeException("Username not specified in the properties file.");
    }

    public String getApplicationPassword() {
        String password = properties.getProperty("App.AdminPassword");
        if(password != null) return password;
        else throw new RuntimeException("Password not specified in the properties file.");
    }
}
