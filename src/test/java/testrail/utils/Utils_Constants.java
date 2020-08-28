package testrail.utils;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils_Constants {

    // response codes
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int FORBIDDEN = 403;

    public static Object convertToObject(Response response) {
        String json = response.body().asString();
        Object res = new Gson().fromJson(json, Object.class);
        return res;
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
