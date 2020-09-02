package testrail.executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import testrail.clients.APIClient;

import static java.lang.System.getProperty;

public class Reports {
    public static final String
            BASE_URL = getProperty("BASE_URL"),
            GET_REPORTS = getProperty("GET_REPORTS"),
            RUN_REPORT = getProperty("RUN_REPORT"),
            TESTRAIL_USERNAME = getProperty("TESTRAIL_USERNAME"),
            TESTRAIL_PASSWORD = getProperty("TESTRAIL_PASSWORD");
    APIClient client;

    public JSONArray get_reports(String project_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONArray) client.sendGet(GET_REPORTS + project_id, statusCode);
    }

    public JSONObject run_report(String report_template_id, int statusCode) {
        client = new APIClient(BASE_URL);
        client.setUser(TESTRAIL_USERNAME);
        client.setPassword(TESTRAIL_PASSWORD);
        return (JSONObject) client.sendGet(RUN_REPORT + report_template_id, statusCode);
    }
}
