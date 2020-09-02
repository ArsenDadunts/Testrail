package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.clients.APIClient;

import java.util.HashMap;
import java.util.Set;

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

    public JSONArray get_milestones(String project_id, HashMap filters, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response;
        if (filters.isEmpty()) {
            response = (JSONArray) client.sendGet(GET_MILESTONES + project_id, statusCode);
        } else {
            StringBuilder filter = new StringBuilder();
            Set keys = filters.keySet();
            for (Object key : keys
            ) {
                filter.append("&").append(key.toString()).append("=").append(filters.get(key.toString()));
            }

            response = (JSONArray) client.sendGet(GET_MILESTONES + project_id + filter, statusCode);
        }
        return response;
    }

    public JSONObject add_milestone(String project_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);

        return (JSONObject) client.sendPost(ADD_MILESTONE + project_id, data, statusCode);
    }

    public JSONObject update_milestone(String milestone_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);

        return (JSONObject) client.sendPost(UPDATE_MILESTONE + milestone_id, data, statusCode);
    }

    public void delete_milestone(String milestone_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        client.sendPost(DELETE_MILESTONE + milestone_id, null, statusCode);
    }
}
