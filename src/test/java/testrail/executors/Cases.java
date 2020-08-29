package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;

import static java.lang.System.getProperty;

public class Cases {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_CASE = getProperty("GET_CASE"),
            GET_CASES = getProperty("GET_CASES"),
            ADD_CASE = getProperty("ADD_CASE"),
            UPDATE_CASE = getProperty("UPDATE_CASE"),
            DELETE_CASE = getProperty("DELETE_CASE"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONObject get_case(String case_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_CASE + case_id, statusCode);
        response.toJSONString();
        return response;
    }

    public JSONArray get_cases(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONArray) client.sendGet(GET_CASES + project_id, statusCode);
    }

    public JSONObject add_case(String section_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(ADD_CASE + section_id, data, statusCode);
    }

    public JSONObject update_case(String case_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(UPDATE_CASE + case_id, data, statusCode);
    }

    public void delete_case(String case_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        client.sendPost(DELETE_CASE + case_id, null, statusCode);
    }
}
