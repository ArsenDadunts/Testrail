package testrail.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class Utils {
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

    public static String generateString() {
        return "" + new Random().nextInt(999999999);
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
