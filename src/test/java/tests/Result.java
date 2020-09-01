package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.Tests;
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
    Tests test = new Tests();

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
        HashMap<Object, Object> filters = new HashMap<>();
        filters.put("limit", 100);
        filters.put("created_by", CREATED_BY_ID);
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
        HashMap filters = new HashMap<>();
        filters.put("limit", 100);
        filters.put("created_by", CREATED_BY_ID);
        JSONArray res = test.get_tests(run_id, OK);
        ArrayList<String> test_ids = new ArrayList<>();
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            test_ids.add(response.get("id").toString());
        }
        // add result for tests
        for (Object test_id : test_ids
        ) {
            JSONObject response = results.add_result(test_id.toString(), payload.addResultData(FAILED, "Test is failed",
                    "version 1.0.0", "TEST-1", ASSIGNED_TO_ID), OK);
            Assert.assertNotNull(response.get("id"));
            Assert.assertNotNull(response.get("test_id"));
            Assert.assertNotNull(response.get("created_by"));
            Assert.assertNotNull(response.get("created_on"));
        }
    }

    @Test
    public void add_result_for_case() {
        //get case
        JSONArray res3 = cases.get_cases(project_id, OK);
        JSONObject case1 = (JSONObject) res3.get(0);
        case_id = case1.get("id").toString();
        // add result for tests
        JSONObject response = results.add_result_for_case(run_id, case_id, payload.addResultData(FAILED, "Test is failed",
                "version 1.0.0", "TEST-1", ASSIGNED_TO_ID), OK);
        Assert.assertNotNull(response.get("id"));
        Assert.assertNotNull(response.get("test_id"));
        Assert.assertNotNull(response.get("created_by"));
        Assert.assertNotNull(response.get("created_on"));
    }
//TODO
//    @Test
//    public void add_result_for_cases() {
//        //get cases
//        JSONArray res3 = cases.get_cases(project_id, OK);
//        JSONObject case1 = (JSONObject) res3.get(0);
//        case_id = case1.get("id").toString();
//        modifyFile(RESOURCES_RESULTS_FOR_CASES_JSON, "case_id", Integer.parseInt(case_id));
//        // add result for tests
//        JSONArray res2 = results.add_results_for_cases(run_id, payload.addResults(RESOURCES_RESULTS_FOR_CASES_JSON), OK);
//        for (Object re : res2) {
//            JSONObject response = (JSONObject) re;
//            Assert.assertNotNull(response.get("id"));
//            Assert.assertNotNull(response.get("test_id"));
//            Assert.assertNotNull(response.get("created_by"));
//            Assert.assertNotNull(response.get("created_on"));
//        }
//    }
//TODO
//    @Test
//    public void add_results() {
//        //get case
//        JSONArray res1 = test.get_tests(run_id, OK);
//        JSONObject test = (JSONObject) res1.get(0);
//        String test_id = test.get("id").toString();
//        modifyFile(RESOURCES_RESULTS_FOR_JSON, "test_id", Integer.parseInt(test_id));
//        // add result for tests
//        JSONArray res2 = results.add_results(run_id, payload.addResults(RESOURCES_RESULTS_FOR_JSON), BAD_REQUEST);
//        for (Object re : res2) {
//            JSONObject response = (JSONObject) re;
//            Assert.assertNotNull(response.get("id"));
//            Assert.assertNotNull(response.get("test_id"));
//            Assert.assertNotNull(response.get("created_by"));
//            Assert.assertNotNull(response.get("created_on"));
//        }
//    }
}
