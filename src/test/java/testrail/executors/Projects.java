package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        JSONObject response = (JSONObject) client.sendGet(GET_PROJECT + project_id, statusCode);
        return response;
    }

    public JSONArray get_projects_without_filters(int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        response = (JSONArray) client.sendGet(GET_PROJECTS, statusCode);
        return response;
    }

    public JSONArray get_projects_with_filters(int statusCode, int is_completed) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        String filter = "&is_completed="+is_completed;
        JSONArray response = (JSONArray) client.sendGet(GET_PROJECTS + filter, statusCode);
        return response;
    }

    public JSONObject add_project(Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        response = (JSONObject) client.sendPost(ADD_PROJECT, data, statusCode);
        return response;
    }

    public JSONObject update_project(String project_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(UPDATE_PROJECT + project_id, data, statusCode);
        return response;
    }

    public JSONObject delete_project(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(DELETE_PROJECT + project_id, null, statusCode);
        return response;
    }
}
