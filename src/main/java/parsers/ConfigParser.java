package parsers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigParser {
    public static String browserType;
    public static String language;
    public static String login;
    public static String password;
    public static String loginUrl;
    public static Long timeOutInSeconds;

    public static void getData(String jsonPath) {
        try {
            Object obj = new JSONParser().parse(new FileReader(jsonPath));
            JSONObject jobj = (JSONObject) obj;
            browserType = (String) jobj.get("browser");
            language = (String) jobj.get("language");
            login = (String) jobj.get("login");
            password = (String) jobj.get("password");
            loginUrl = (String) jobj.get("loginUrl");
            timeOutInSeconds = (Long) jobj.get("timeOut");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
