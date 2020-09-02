package testrail.executors;

import org.json.simple.JSONArray;
import testrail.clients.APIClient;

import static java.lang.System.getProperty;

public class Templates {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_TEMPLATES = getProperty("GET_TEMPLATES"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONArray get_templates(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONArray) client.sendGet(GET_TEMPLATES + project_id, statusCode);
    }
}
