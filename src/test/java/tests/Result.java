package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.*;
import testrail.payloads.ResultPayload;

import java.util.ArrayList;
import java.util.HashMap;

import static testrail.utils.Utils_Constants.*;

public class Result {
    String project_id, run_id, case_id;
    Projects project = new Projects();
    Runs run = new Runs();
    Cases cases = new Cases();
    Results results = new Results();
    ResultPayload payload = new ResultPayload();

    @BeforeClass
    public void setup() {
        //get project
        JSONArray res1 = project.get_projects_with_filters(OK, 0);
        JSONObject project = (JSONObject) res1.get(0);
        project_id = project.get("id").toString();
        //get run
        HashMap<String, Integer> filters = new HashMap<>();
        filters.put("is_completed", 0);
        JSONArray res2 = run.get_runs(project_id, filters, OK);
        JSONObject run = (JSONObject) res2.get(0);
        run_id = run.get("id").toString();
    }

    @Test
    public void get_results_for_case() {
        //get cases
        JSONArray res3 = cases.get_cases(project_id, OK);
        JSONObject case1 = (JSONObject) res3.get(0);
        case_id = case1.get("id").toString();
        //get_results_for_case
        HashMap<String, Integer> filters = new HashMap<>();
        filters.put("limit", 100);
        JSONArray res = results.get_results_for_case(run_id, case_id, OK, filters);
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            Assert.assertNotNull(response.get("id"));
            Assert.assertNotNull(response.get("test_id"));
            Assert.assertNotNull(response.get("created_by"));
            Assert.assertNotNull(response.get("created_on"));
        }
    }

    @Test
    public void get_results_for_run() {
        //get_results_for_run
        HashMap<String, Integer> filters = new HashMap<>();
        filters.put("limit", 100);
        filters.put("created_by", 1);
        JSONArray res = results.get_results_for_run(run_id, filters, OK);
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            Assert.assertNotNull(response.get("id"));
            Assert.assertNotNull(response.get("test_id"));
            Assert.assertNotNull(response.get("created_by"));
            Assert.assertNotNull(response.get("created_on"));
        }
    }

    @Test
    public void add_result() {
        //get_results_for_run
        HashMap<String, Integer> filters = new HashMap<>();
        filters.put("limit", 100);
        filters.put("created_by", 1);
        JSONArray res = results.get_results_for_run(run_id, filters, OK);
        ArrayList<String> test_ids = new ArrayList<>();
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            test_ids.add(response.get("test_id").toString());

        }
        // add result for tests
        for (Object test_id : test_ids
        ) {
            JSONObject response = results.add_result(test_id.toString(), payload.addResultData(FAILED, "Test is failed",
                    "version 1.0.0", "TEST-1", 1), OK);
            Assert.assertNotNull(response.get("id"));
            Assert.assertNotNull(response.get("test_id"));
            Assert.assertNotNull(response.get("created_by"));
            Assert.assertNotNull(response.get("created_on"));
        }
    }
}
