package testrail.executors;

import org.json.simple.JSONArray;
import testrail.APIClient;
import testrail.APIException;
import java.io.IOException;


import static java.lang.System.getProperty;

public class Priorities {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_PRIORITIES = getProperty("GET_PRIORITIES"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public JSONArray get_priorities(int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        try {
                response = (JSONArray) client.sendGet(GET_PRIORITIES, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }
}
