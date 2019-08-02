package config;

import org.json.simple.parser.ParseException;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class ConfigProvider {

    public static Object getConfigObject(String testEnvironment) throws IOException, ParseException {
        String projectDir = System.getProperty("user.dir");
        String pathSep = File.separator;
        String configFilePath = projectDir + pathSep + "src" + pathSep + "main" + pathSep + "resources" + pathSep + "environment.json";
        System.out.println("Config file: " + configFilePath);
        System.out.println("Test environment: " + testEnvironment);
        return FileUtils.getJsonFileAsJSONObject(configFilePath).get(testEnvironment);
    }
}
