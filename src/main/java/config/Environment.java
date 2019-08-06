package config;

import common.MyLogger;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Environment {

    public static String baseUrl;
    public static String postsEndpoint;
    public static String commentsEndpoint;
    public static String usersEndpoint;
    private Logger log = MyLogger.log;

    public Environment() {
        log.info("Setting up the test environment");
        String testEnvironment = System.getProperty("ENV", "staging");
        String commonConfigKey = "common";
        JSONObject envConfig = new JSONObject();
        JSONObject commonConfig = new JSONObject();
        try {
            envConfig = (JSONObject) ConfigProvider.getConfigObject(testEnvironment);
            commonConfig = (JSONObject) ConfigProvider.getConfigObject(commonConfigKey);
        } catch (IOException | ParseException e) {
            log.error("Something went wrong while parsing the environment data: " + e.getMessage());
        }
        if (envConfig == null) {
            log.error("The test environment '" + testEnvironment + "' was not found. Please provide a valid test environment name.");
        }
        Environment.baseUrl = envConfig.get("baseUrl").toString();
        Environment.postsEndpoint = commonConfig.get("posts").toString();
        Environment.commentsEndpoint = commonConfig.get("comments").toString();
        Environment.usersEndpoint = commonConfig.get("users").toString();
        log.info("Base URL: " + Environment.baseUrl);
    }
}
