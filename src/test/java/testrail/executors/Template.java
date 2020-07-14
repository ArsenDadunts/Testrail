package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getProperty;

public class Template {
    public static final String BASE_URL = getProperty("BASE_URL");
    public static final String TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME");
    public static final String TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public void get_templates(String projectId) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray request = null;
        try {
            request = (JSONArray) client.sendGet("get_templates/" + projectId);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        for (Object object : request
        ) {
            System.out.println(object.toString());
        }
    }
}
