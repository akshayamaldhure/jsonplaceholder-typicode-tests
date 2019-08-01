package config;

import utils.FileUtils;

import java.io.File;

public class ConfigProvider {

    public static Object getConfigObject(String testEnvironment) {
        String projectDir = System.getProperty("user.dir");
        String pathSep = File.separator;
        String configFilePath = projectDir + pathSep + "src" + pathSep + "main" + pathSep + "java" + pathSep + "config" + pathSep + "environment.json";
        System.out.println("Config file: " + configFilePath);
        System.out.println("Test environment: " + testEnvironment);
        return FileUtils.getJsonFileAsJSONObject(configFilePath).get(testEnvironment);
    }
}
