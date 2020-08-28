package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.getProperty;

public class Sections {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            ADD_SECTION = getProperty("ADD_SECTION"),
            GET_SECTION = getProperty("GET_SECTION"),
            GET_SECTIONS = getProperty("GET_SECTIONS"),
            UPDATE_SECTION = getProperty("UPDATE_SECTION"),
            DELETE_SECTION = getProperty("DELETE_SECTION"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;


    public JSONObject get_section(String section_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendGet(GET_SECTION + section_id, statusCode);
        response.toJSONString();

        return response;
    }

    public JSONArray get_sections(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = (JSONArray) client.sendGet(GET_SECTIONS + project_id, statusCode);
        return response;
    }

    public JSONObject add_section(String project_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(ADD_SECTION + project_id, data, statusCode);

        return response;
    }

    public JSONObject update_section(String section_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(UPDATE_SECTION + section_id, data, statusCode);

        return response;
    }

    public JSONObject delete_section(String section_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = (JSONObject) client.sendPost(DELETE_SECTION + section_id, null, statusCode);

        return response;
    }
}
