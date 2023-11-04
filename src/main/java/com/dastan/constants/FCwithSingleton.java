package com.dastan.constants;

import lombok.Getter;

@Getter
public class FCwithSingleton {

    //Single instance for a class at a particular point of time
    //creational design pattern
    private static FCwithSingleton INSTANCE = null;
    private FCwithSingleton(){}

    public static synchronized FCwithSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FCwithSingleton();
        }
        return INSTANCE;
    }
    private final String requestJsonFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\";
    private final String responseJsonFolderPath = System.getProperty("user.dir") + "\\output\\";
}
