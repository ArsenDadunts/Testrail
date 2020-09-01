package testrail.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils_Constants {
    // response codes
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int FORBIDDEN = 403;
    //test result
    public static final int PASSED = 1;
    public static final int BLOCKED = 2;
    public static final int RETEST = 4;
    public static final int UNTESTED = 3;
    public static final int FAILED = 5;
    //ids
    public static final String EXAMPLE_TEST_ID = "1";
    public static final String ASSIGNED_TO_ID = "1";
    public static final String CREATED_BY_ID = "1";
    //example files
    public static final String RESOURCES_RESULTS_FOR_CASES_JSON = "src/test/resources/results_for_cases.json";
    public static final String RESOURCES_RESULTS_FOR_JSON = "src/test/resources/results.json";


    public static Object convertToObject(Response response) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(response.body().asString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Object convertToArray(Response response) {
        JSONParser parser = new JSONParser();
        JSONArray json = null;
        try {
            json = (JSONArray) parser.parse(response.body().asString());
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

//    public static void modifyFile(String filePath, String key, int id) {
//        JSONObject object, object1;
//        HashMap map = new HashMap();
//        FileReader reader = null;
//        FileWriter writer = null;
//        JSONParser parser = new JSONParser();
//        try {
//            reader = new FileReader(filePath);
//            object = (JSONObject) parser.parse(reader);
//            JSONArray array = (JSONArray) object.get("results");
//            map = array.
//            object1 = (JSONObject) array.get(0);
//            array.remove(object1);
//            object1.put(key, id);
//            array.add(object1);
//            writer = new FileWriter(filePath);
//            writer.write(array.toJSONString());
//            System.out.println("Started print file:\n");
//            printFile(filePath);
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//

    public static long setUnixTimeStamp() {
        Date date = new Date();
        return date.getTime() / 1000L;
    }

    public static long setUnixTimeStamp(int days) {
        Date date = new Date();
        Date date1 = new Date(date.getYear(), date.getMonth(), date.getDay() + days);
        return date1.getTime() / 1000L;
    }
}
