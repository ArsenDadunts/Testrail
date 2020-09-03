package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.clients.APIClient;

import static java.lang.System.getProperty;

public class Projects {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            ADD_PROJECT = getProperty("ADD_PROJECT"),
            GET_PROJECT = getProperty("GET_PROJECT"),
            GET_PROJECTS = getProperty("GET_PROJECTS"),
            UPDATE_PROJECT = getProperty("UPDATE_PROJECT"),
            DELETE_PROJECT = getProperty("DELETE_PROJECT"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public JSONObject get_project(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendGet(GET_PROJECT + project_id, statusCode);
    }

    public JSONArray get_projects_without_filters(int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONArray) client.sendGet(GET_PROJECTS, statusCode);
    }

    public JSONArray get_projects_with_filters(int statusCode, int is_completed) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        String filter = "&is_completed=" + is_completed;
        return (JSONArray) client.sendGet(GET_PROJECTS + filter, statusCode);
    }

    public JSONObject add_project(Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response;
        response = (JSONObject) client.sendPost(ADD_PROJECT, data, statusCode);
        return response;
    }

    public JSONObject update_project(String project_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendPost(UPDATE_PROJECT + project_id, data, statusCode);
    }

    public void delete_project(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        client.sendPost(DELETE_PROJECT + project_id, null, statusCode);
    }
}
