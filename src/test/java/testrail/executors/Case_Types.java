package testrail.executors;

import org.json.simple.JSONArray;
import testrail.clients.APIClient;

import static java.lang.System.getProperty;

public class Case_Types {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_CASE_TYPES = getProperty("GET_CASE_TYPES"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public JSONArray get_case_types(int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONArray) client.sendGet(GET_CASE_TYPES, statusCode);
    }
}
