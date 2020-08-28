package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.getProperty;

public class Runs {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            ADD_RUN = getProperty("ADD_RUN"),
            GET_RUN = getProperty("GET_RUN"),
            GET_RUNS = getProperty("GET_RUNS"),
            UPDATE_RUN = getProperty("UPDATE_RUN"),
            CLOSE_RUN = getProperty("CLOSE_RUN"),
            DELETE_RUN = getProperty("DELETE_RUN"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public JSONObject get_run(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_RUN + run_id, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONArray get_runs(String project_id, int statusCode, HashMap filters) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RUNS + project_id, statusCode);
        } else {
            String filter = "";
            ArrayList keys = (ArrayList) filters.keySet();
            for (int i = 0; i < filters.size(); i++) {
                filter += "&" + keys.get(i) + filters.get(keys.get(i));
            }
            response = (JSONArray) client.sendGet(GET_RUNS + project_id + filter, statusCode);
        }
        response.toJSONString();

        return response;
    }

    public JSONObject add_run(String project_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(ADD_RUN + project_id, data, statusCode);

        return response;
    }

    public JSONObject update_run(String run_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(UPDATE_RUN + run_id, data, statusCode);

        return response;
    }

    public JSONObject close_run(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(CLOSE_RUN + run_id, null, statusCode);

        return response;
    }

    public JSONObject delete_run(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(DELETE_RUN + run_id, null, statusCode);

        return response;
    }
}
