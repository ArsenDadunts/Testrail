package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.clients.APIClient;

import java.util.HashMap;
import java.util.Set;

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

    public JSONArray get_runs(String project_id, HashMap filters, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_RUNS + project_id, statusCode);
        } else {
            StringBuilder filter = new StringBuilder();
            Set keys = filters.keySet();
            for (Object key: keys
                 ) {
                filter.append("&").append(key.toString()).append("=").append(filters.get(key));
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
        return (JSONObject) client.sendPost(ADD_RUN + project_id, data, statusCode);
    }

    public JSONObject update_run(String run_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(UPDATE_RUN + run_id, data, statusCode);
    }

    public JSONObject close_run(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(CLOSE_RUN + run_id, null, statusCode);
    }

    public void delete_run(String run_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        client.sendPost(DELETE_RUN + run_id, null, statusCode);
    }
}
