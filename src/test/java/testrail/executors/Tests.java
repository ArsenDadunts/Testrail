package testrail.executors;

import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;

import static java.lang.System.getProperty;

public class Tests {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_TEST = getProperty("GET_TEST"),
            GET_TESTS = getProperty("GET_TESTS"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONObject get_test(String test_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_TEST + test_id, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONObject get_tests(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_TESTS + run_id, statusCode);
        response.toJSONString();

        return response;
    }
}
