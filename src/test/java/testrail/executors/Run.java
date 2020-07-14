package testrail.executors;

import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getProperty;

public class Run {
    public static final String BASE_URL = getProperty("BASE_URL");
    public static final String TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME");
    public static final String TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public void add_run(String project_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("add_run/" + project_id, data);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void update_run(String run_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendPost("update_run/" + run_id, data);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void delete_run(String run_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendGet("delete_run/" + run_id);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void get_run(String run_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendGet("get_run/" + run_id);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    public void get_runs(String project_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject request = null;
        try {
            request = (JSONObject) client.sendGet("get_runs/" + project_id);
            request.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

    }
}
