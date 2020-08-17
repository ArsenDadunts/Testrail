package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.APIClient;
import testrail.APIException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.getProperty;

public class Case {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_CASE = getProperty("GET_CASE"),
            GET_CASES = getProperty("GET_CASES"),
            ADD_CASE = getProperty("ADD_CASE"),
            UPDATE_CASE = getProperty("UPDATE_CASE"),
            DELETE_CASE = getProperty("DELETE_CASE"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public Object get_case(String id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendGet(GET_CASE + id, statusCode);
            response.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object get_cases(String project_id, int statusCode, HashMap filters) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONArray response = null;
        try {
            if (filters.isEmpty()) {
                response = (JSONArray) client.sendGet(GET_CASES + project_id, statusCode);
            } else {
                String filter = "";
                ArrayList keys = (ArrayList) filters.keySet();
                for (int i = 0; i < filters.size(); i++) {
                    filter += "&" + keys.get(i) + filters.get(keys.get(i));
                }
                response = (JSONArray) client.sendGet(GET_CASES + project_id + filter, statusCode);
            }
            response.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object add_case(String section_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost(ADD_CASE + section_id, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object update_case(String case_id, Object data, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost(UPDATE_CASE + case_id, data, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Object delete_case(String case_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        JSONObject response = null;
        try {
            response = (JSONObject) client.sendPost(DELETE_CASE + case_id, null, statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }
        return response;
    }
}
