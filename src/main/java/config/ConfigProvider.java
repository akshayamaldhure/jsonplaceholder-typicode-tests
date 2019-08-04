package config;

import org.json.simple.parser.ParseException;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class ConfigProvider {

    public static Object getConfigObject(String configKey) throws IOException, ParseException {
        String projectDir = System.getProperty("user.dir");
        String pathSep = File.separator;
        String configFilePath = projectDir + pathSep + "src" + pathSep + "main" + pathSep + "resources" + pathSep + "environments.json";
        System.out.println("Configuration key: " + configKey);
        return FileUtils.getJsonFileAsJSONObject(configFilePath).get(configKey);
    }
}
