package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getProperty;

public class Case {
    public static final String BASE_URL = getProperty("BASE_URL");
    public static final String TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME");
    public static final String TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public void get_case(String id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendGet("get_case/" + id);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void get_cases(String projectId) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray request = null;
        try {
            request = (JSONArray) client.sendGet("get_cases/" + projectId);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void add_case(String section_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("add_case/" + section_id, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void update_case(String case_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("update_case/" + case_id, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void delete_case(String case_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("delete_case/" + case_id, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
