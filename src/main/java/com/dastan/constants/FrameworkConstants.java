package com.dastan.constants;

import lombok.Getter;

public final class FrameworkConstants {

    private static @Getter final String requestJsonFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\jsons\\";
    private static @Getter final String responseJsonFolderPath = System.getProperty("user.dir") + "\\output\\";
    private static @Getter final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

}
