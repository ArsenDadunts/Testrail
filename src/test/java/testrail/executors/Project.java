package testrail.executors;

import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getProperty;

public class Project {
    public static final String BASE_URL = getProperty("BASE_URL");
    public static final String TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME");
    public static final String TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public void get_project(String project_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendGet("get_project/" + project_id);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void get_projects() {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendGet("get_projects");
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void add_project(Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("add_project", data);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void update_project(String project_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("update_project/" + project_id, data);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void delete_project(String project_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("delete_project/" + project_id, null);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }
}
