package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.Map;

import static java.lang.System.getProperty;

public class Result {
    public static final String BASE_URL = getProperty("BASE_URL");
    public static final String TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME");
    public static final String TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public Object get_results(String test_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendGet("get_results/" + test_id, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object get_results_for_case(String run_id, String case_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendGet("get_results_for_case/" + run_id + "/" + case_id, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object get_results_for_run(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendGet("get_results_for_run/" + run_id, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object add_result(String test_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost("add_result/" + test_id, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object add_results(String test_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost("add_result/" + test_id, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object add_result_for_case(String run_id, String case_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost("add_result_for_case/" + run_id + "/" + case_id, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object add_results_for_cases(String run_id, String case_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost("add_result_for_case/" + run_id + "/" + case_id, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }
}
