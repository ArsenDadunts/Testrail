package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;

import static java.lang.System.getProperty;

public class Case_Fields {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_CASE_FIELDS = getProperty("GET_CASE_FIELDS"),
            ADD_CASE_FIELD = getProperty("ADD_CASE_FIELD"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONArray get_case_fields(int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONArray) client.sendGet(GET_CASE_FIELDS, statusCode);
    }

    public JSONObject add_case_field(Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(ADD_CASE_FIELD, data, statusCode);
    }
}
