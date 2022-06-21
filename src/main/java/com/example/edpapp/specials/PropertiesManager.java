package com.example.edpapp.specials;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    //singleton class for loading properties
    private static PropertiesManager instance;
    private Properties prop;
    private PropertiesManager() {
        //load properties
        try{
            InputStream input = new FileInputStream("src/main/resources/config.properties");
            this.prop = new Properties();
            this.prop.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }
    public String getProperty(String key) {
        return this.prop.getProperty(key);
    }
}
