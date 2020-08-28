package testrail.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

public class Utils_Constants {
    // response codes
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int FORBIDDEN = 403;

    public static Object convertToObject(Response response) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json= (JSONObject) parser.parse(response.body().asString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Object convertToArray(Response response) {
        JSONParser parser = new JSONParser();
        JSONArray json = null;
        try {
            json= (JSONArray) parser.parse(response.body().asString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Map convertObjectToMap(Object objectToConvert) {
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(objectToConvert, Map.class);
        return map;
    }

    public static String generateTitle() {
        return "Example test " + new Random().nextInt(999999999);
    }

    public static int generateRandomInt() {
        return new Random().nextInt(65535);
    }

    public static String getDate() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ZonedDateTime.now().format(formatter2);
    }
}
