package config;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Environment {

    public String baseUrl;
    public String postsEndpoint;
    public String commentsEndpoint;

    public Environment() {
        String testEnvironment = System.getProperty("ENV", "staging");
        JSONObject configObject = null;
        try {
            configObject = (JSONObject) ConfigProvider.getConfigObject(testEnvironment);
        } catch (IOException | ParseException e) {
            System.out.println("Something went wrong while parsing the environment data: " + e.getMessage());
        }
        if (configObject == null) {
            System.out.println("The test environment '" + testEnvironment + "' was not found. Please provide a valid test environment name.");
        }
        this.baseUrl = configObject.get("baseUrl").toString();
        this.postsEndpoint = configObject.get("posts").toString();
        this.commentsEndpoint = configObject.get("comments").toString();
        System.out.println("Base URL: " + this.baseUrl);
    }
}
