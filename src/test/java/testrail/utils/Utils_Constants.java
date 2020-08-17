package testrail.utils;

import com.google.gson.Gson;
import io.restassured.response.Response;

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
}
