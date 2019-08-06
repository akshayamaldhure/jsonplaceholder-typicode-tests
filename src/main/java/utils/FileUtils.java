package utils;

import common.MyLogger;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileUtils {

    private static Logger log = MyLogger.log;

    public static JSONObject getJsonFileAsJSONObject(String filePath) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException exception) {
            log.error("The configuration file " + filePath + " was not found.");
            exception.printStackTrace();
        }
        jsonObject = (JSONObject) jsonParser.parse(fileReader);
        return jsonObject;
    }
}
