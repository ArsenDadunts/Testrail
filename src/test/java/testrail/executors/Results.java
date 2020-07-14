package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getProperty;

public class Results {
    public static final String BASE_URL = getProperty("BASE_URL");
    public static final String TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME");
    public static final String TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public void get_results(String test_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray request = null;
        try {
            request = (JSONArray) client.sendGet("get_results/" + test_id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < request.size(); i++) {
            System.out.println(request.get(i));
        }
    }

    public void get_results_for_case(String run_id, String case_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        try {
            JSONObject request = (JSONObject) client.sendGet("get_results_for_case/" + run_id + "/" + case_id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void get_results_for_run(String run_id) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        try {
            JSONObject request = (JSONObject) client.sendGet("get_results_for_run/" + run_id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void add_result(String test_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        try {
            JSONObject request = (JSONObject) client.sendPost("add_result/" + test_id, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void add_results(String test_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        try {
            JSONObject request = (JSONObject) client.sendPost("add_result/" + test_id, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void add_result_for_case(String run_id, String case_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        try {
            JSONObject request = (JSONObject) client.sendPost("add_result_for_case/" + run_id + "/" + case_id, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }

    public void add_results_for_cases(String run_id, String case_id, Map data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        try {
            JSONObject request = (JSONObject) client.sendPost("add_result_for_case/" + run_id + "/" + case_id, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
