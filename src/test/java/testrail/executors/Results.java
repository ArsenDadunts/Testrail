package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.getProperty;

public class Results {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            ADD_RESULT = getProperty("ADD_RESULT"),
            ADD_RESULTS = getProperty("ADD_RESULTS"),
            ADD_RESULT_FOR_CASE = getProperty("ADD_RESULT_FOR_CASE"),
            ADD_RESULTS_FOR_CASES = getProperty("ADD_RESULTS_FOR_CASES"),
            GET_RESULTS = getProperty("GET_RESULTS"),
            GET_RESULTS_FOR_CASE = getProperty("GET_RESULTS_FOR_CASE"),
            GET_RESULTS_FOR_RUN = getProperty("GET_RESULTS_FOR_RUN"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONArray get_results(String test_id, int statusCode, HashMap filters) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RESULTS + test_id, statusCode);
        } else {
            String filter = "";
            ArrayList keys = (ArrayList) filters.keySet();
            for (int i = 0; i < filters.size(); i++) {
                filter += "&" + keys.get(i) + filters.get(keys.get(i));
            }
            response = (JSONArray) client.sendGet(GET_RESULTS + test_id + filter, statusCode);
        }
        response.toJSONString();

        return response;
    }

    public JSONArray get_results_for_case(String run_id, String case_id, int statusCode, HashMap filters) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RESULTS_FOR_CASE + run_id + "/" + case_id, statusCode);
        } else {
            String filter = "";
            ArrayList keys = (ArrayList) filters.keySet();
            for (int i = 0; i < filters.size(); i++) {
                filter += "&" + keys.get(i) + filters.get(keys.get(i));
            }
            response = (JSONArray) client.sendGet(GET_RESULTS_FOR_CASE + case_id + filter, statusCode);
        }
        response.toJSONString();

        return response;
    }

    public JSONArray get_results_for_run(String run_id, int statusCode, HashMap filters) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RESULTS_FOR_RUN + run_id, statusCode);
        } else {
            String filter = "";
            ArrayList keys = (ArrayList) filters.keySet();
            for (int i = 0; i < filters.size(); i++) {
                filter += "&" + keys.get(i) + filters.get(keys.get(i));
            }
            response = (JSONArray) client.sendGet(GET_RESULTS_FOR_RUN + run_id + filter, statusCode);
        }
        response.toJSONString();

        return response;
    }

    public JSONObject add_result(String test_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(ADD_RESULT + test_id, data, statusCode);

        return response;
    }

    public JSONObject add_result_for_case(String run_id, String case_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(ADD_RESULT_FOR_CASE + run_id + "/" + case_id, data, statusCode);

        return response;
    }

    public JSONArray add_results(String run_id, int statusCode, Object data) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        response = (JSONArray) client.sendPost(ADD_RESULTS + run_id, data, statusCode);

        return response;
    }

    public JSONObject add_results_for_cases(String run_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(ADD_RESULTS_FOR_CASES + run_id, data, statusCode);

        return response;
    }
}
