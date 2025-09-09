package com.bookstoreapi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop=new Properties();
    public static String getConfigValue(String key){
        String configFilePath="config.properties";
        try(FileInputStream fileInputStream=new FileInputStream(configFilePath)) {
            prop.load(fileInputStream);
            if(prop.getProperty(key)!=null)
               return prop.getProperty(key);
            else
               throw new IllegalArgumentException("Key "+key+ " not found in "+configFilePath+" file");
        }
        catch (IOException ex){
          throw new RuntimeException("Failed to load from "+configFilePath,ex);
        }
    }
}
