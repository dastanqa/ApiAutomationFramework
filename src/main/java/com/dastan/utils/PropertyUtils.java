package com.dastan.utils;

import com.dastan.constants.FrameworkConstants;
import com.dastan.enums.PropertiesType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertyUtils {
    private PropertyUtils(){}

    //read content from property file and store it in hashmap
    //read the content only once and store it in some java collection

    private static Properties properties = new Properties();
    private static Map<String,String> map = new HashMap<>();

    //Generic exception,
    static {

        try (FileInputStream inputStream = new FileInputStream(FrameworkConstants.getPropertyFilePath())){
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        properties.entrySet().forEach(e->map.put(String.valueOf(e.getKey()),String.valueOf(e.getValue())));
    }

    public static String getValue(PropertiesType key){
        return map.get(key.name().toLowerCase());
    }
}
