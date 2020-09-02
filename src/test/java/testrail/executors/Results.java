package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.clients.APIClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
        JSONArray response;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RESULTS + test_id, statusCode);
        } else {
            StringBuilder filter = new StringBuilder();
            ArrayList keys = (ArrayList) filters.keySet();
            for (int i = 0; i < filters.size(); i++) {
                filter.append("&").append(keys.get(i)).append("=").append(filters.get(keys.get(i)));
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
        JSONArray response;
        StringBuilder builder = new StringBuilder();
        builder.append(GET_RESULTS_FOR_CASE).append(run_id).append("/").append(case_id);
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(builder.toString(), statusCode);
        } else {
            StringBuilder filter = new StringBuilder();
            Set keys = filters.keySet();
            for (Object key : keys
            ) {
                filter.append("&").append(key.toString()).append("=").append(filters.get(key.toString()));
            }
            response = (JSONArray) client.sendGet(builder.toString() + filter, statusCode);
        }
        response.toJSONString();

        return response;
    }

    public JSONArray get_results_for_run(String run_id, HashMap filters, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RESULTS_FOR_RUN + run_id, statusCode);
        } else {
            StringBuilder filter = new StringBuilder();
            Set keys = filters.keySet();
            for (Object key : keys
            ) {
                filter.append("&").append(key.toString()).append("=").append(filters.get(key.toString()));
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

        return (JSONObject) client.sendPost(ADD_RESULT + test_id, data, statusCode);
    }

    public JSONObject add_result_for_case(String run_id, String case_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(ADD_RESULT_FOR_CASE + run_id + "/" + case_id, data, statusCode);
    }

    public JSONArray add_results(String run_id, Object data,  int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response;
        response = (JSONArray) client.sendPost(ADD_RESULTS + run_id, data, statusCode);

        return response;
    }

    public JSONArray add_results_for_cases(String run_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);

        return (JSONArray) client.sendPost(ADD_RESULTS_FOR_CASES + run_id, data, statusCode);
    }
}
