package config;

import org.json.simple.JSONObject;

public class Environment {

    String baseUrl;
    String postsEndpoint;
    String commentsEndpoint;

    Environment() {
        String testEnvironment = System.getProperty("ENV", "staging");
        JSONObject configObject = (JSONObject) ConfigProvider.getConfigObject(testEnvironment);
        this.baseUrl = configObject.get("baseUrl").toString();
        this.postsEndpoint = configObject.get("posts").toString();
        this.commentsEndpoint = configObject.get("comments").toString();
        System.out.println("Base URL: " + this.baseUrl);
    }
}
