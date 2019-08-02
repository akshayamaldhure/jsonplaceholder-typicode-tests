package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    public static JSONObject getJsonFileAsJSONObject(String filePath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            FileReader fileReader = new FileReader(filePath);
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException | ParseException exception) {
            System.out.println("Something went wrong while reading or parsing the file " + filePath);
            exception.printStackTrace();
        }
        return jsonObject;
    }
}
