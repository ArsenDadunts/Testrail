package testrail.executors;

import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;

import static java.lang.System.getProperty;

public class Suites {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_SUITE = getProperty("GET_SUITE"),
            GET_SUITES = getProperty("GET_SUITES"),
            ADD_SUITE = getProperty("ADD_SUITE"),
            UPDATE_SUITE = getProperty("UPDATE_SUITE"),
            DELETE_SUITE = getProperty("DELETE_SUITE"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONObject get_suite(String suite_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_SUITE + suite_id, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONObject get_suites(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_SUITES + project_id, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONObject update_suite(String suite_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(UPDATE_SUITE + suite_id, data, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONObject delete_suite(String suite_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(DELETE_SUITE + suite_id, null, statusCode);
        response.toJSONString();

        return response;
    }
}
