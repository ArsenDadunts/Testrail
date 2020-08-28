package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.getProperty;

public class Milestones {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            ADD_MILESTONE = getProperty("ADD_MILESTONE"),
            GET_MILESTONE = getProperty("GET_MILESTONE"),
            GET_MILESTONES = getProperty("GET_MILESTONES"),
            UPDATE_MILESTONE = getProperty("UPDATE_MILESTONE"),
            DELETE_MILESTONE = getProperty("DELETE_MILESTONE"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public JSONObject get_milestone(String milestone_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_MILESTONE + milestone_id, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONArray get_milestones(String project_id, int statusCode, HashMap filters) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_MILESTONES + project_id, statusCode);
        } else {
            String filter = "";
            ArrayList keys = (ArrayList) filters.keySet();
            for (int i = 0; i < filters.size(); i++) {
                filter += "&" + keys.get(i) + filters.get(keys.get(i));
            }
            response = (JSONArray) client.sendGet(GET_MILESTONES + project_id + filter, statusCode);
        }
        response.toJSONString();

        return response;
    }

    public JSONObject add_milestone(String project_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(ADD_MILESTONE + project_id, data, statusCode);

        return response;
    }

    public JSONObject update_milestone(String milestone_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(UPDATE_MILESTONE + milestone_id, data, statusCode);

        return response;
    }

    public JSONObject delete_milestone(String milestone_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(DELETE_MILESTONE + milestone_id, null, statusCode);

        return response;
    }
}
