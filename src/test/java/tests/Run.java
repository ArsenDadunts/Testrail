package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.*;
import testrail.payloads.RunPayload;

import java.util.ArrayList;
import java.util.HashMap;

import static testrail.utils.Utils_Constants.CREATED_BY_ID;
import static testrail.utils.Utils_Constants.OK;

public class Run {
    ArrayList<Object> caseIds = new ArrayList<>();
    String project_id;
    Runs runs = new Runs();
    Cases get_case;
    Projects project;
    RunPayload payload = new RunPayload();

    @BeforeClass
    public void setup(){
        //get project
        project = new Projects();
        JSONArray projects = project.get_projects_with_filters(OK,0);
        JSONObject project = (JSONObject) projects.get(0);
        project_id = project.get("id").toString();
        //get cases
        get_case = new Cases();
        JSONArray cases = get_case.get_cases(project_id, OK);
        for (Object object : cases) {
            JSONObject res = (JSONObject) object;
            caseIds.add(res.get("id"));
        }
        payload.case_ids = caseIds;
    }

    @Test
    public void add_run(){
        //add run
        JSONObject response = runs.add_run(project_id, payload,OK);
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("assignedto_id").toString(), String.valueOf(payload.assignedto_id));
        Assert.assertNotNull(response.get("blocked_count"));
        Assert.assertNull(response.get("completed_on"));
        Assert.assertEquals(response.get("created_by").toString(), "1");
        Assert.assertNotNull(response.get("created_on"));
        Assert.assertEquals(response.get("include_all"), false);
        Assert.assertEquals(response.get("is_completed"), false);
        Assert.assertEquals(response.get("milestone_id").toString(), String.valueOf(payload.milestone_id));
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("passed_count").toString(), "0");
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertEquals(response.get("suite_id").toString(), String.valueOf(payload.suite_id));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void update_run(){
        //add run
        JSONObject res = runs.add_run(project_id, payload,OK);
        Assert.assertNotNull(res.get("id"));
        String run_id = res.get("id").toString();
        //get run
        payload.include_all =true;
        payload.case_ids = null;
        payload.description = "Updated Run description "+run_id;
        JSONObject response = runs.update_run(run_id, payload, OK);
        Assert.assertEquals(res.get("id").toString(), run_id);
        Assert.assertEquals(response.get("assignedto_id").toString(), String.valueOf(payload.assignedto_id));
        Assert.assertNotNull(response.get("blocked_count"));
        Assert.assertNull(response.get("completed_on"));
        Assert.assertEquals(response.get("created_by").toString(), "1");
        Assert.assertNotNull(response.get("created_on"));
        Assert.assertEquals(response.get("include_all"), true);
        Assert.assertEquals(response.get("is_completed"), false);
        Assert.assertEquals(response.get("milestone_id").toString(), String.valueOf(payload.milestone_id));
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("passed_count").toString(), "0");
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertEquals(response.get("suite_id").toString(), String.valueOf(payload.suite_id));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void get_run(){
        //add run
        JSONObject res = runs.add_run(project_id, payload,OK);
        Assert.assertNotNull(res.get("id"));
        String run_id = res.get("id").toString();
        //get run
        JSONObject response = runs.get_run(run_id, OK);
        Assert.assertEquals(res.get("id").toString(), run_id);
        Assert.assertEquals(response.get("assignedto_id").toString(), String.valueOf(payload.assignedto_id));
        Assert.assertNotNull(response.get("blocked_count"));
        Assert.assertNull(response.get("completed_on"));
        Assert.assertEquals(response.get("created_by").toString(), "1");
        Assert.assertNotNull(response.get("created_on"));
        Assert.assertEquals(response.get("include_all"), false);
        Assert.assertEquals(response.get("is_completed"), false);
        Assert.assertEquals(response.get("milestone_id").toString(), String.valueOf(payload.milestone_id));
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("passed_count").toString(), "0");
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertEquals(response.get("suite_id").toString(), String.valueOf(payload.suite_id));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void close_run(){
        //add run
        JSONObject res = runs.add_run(project_id, payload,OK);
        Assert.assertNotNull(res.get("id"));
        String run_id = res.get("id").toString();
        //close run
        JSONObject response = runs.close_run(run_id, OK);
        Assert.assertEquals(res.get("id").toString(), run_id);
        Assert.assertEquals(response.get("assignedto_id").toString(), String.valueOf(payload.assignedto_id));
        Assert.assertNotNull(response.get("blocked_count"));
        Assert.assertNotNull(response.get("completed_on"));
        Assert.assertEquals(response.get("created_by").toString(), "1");
        Assert.assertNotNull(response.get("created_on"));
        Assert.assertEquals(response.get("include_all"), false);
        Assert.assertEquals(response.get("is_completed"), true);
        Assert.assertEquals(response.get("milestone_id").toString(), String.valueOf(payload.milestone_id));
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("passed_count").toString(), "0");
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertEquals(response.get("suite_id").toString(), String.valueOf(payload.suite_id));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void delete_run(){
        //add run
        JSONObject res = runs.add_run(project_id, payload,OK);
        Assert.assertNotNull(res.get("id"));
        String run_id = res.get("id").toString();
        //delete run
        runs.delete_run(run_id, OK);
    }

    @Test
    public void get_runs(){
        //get runs
        HashMap<String, String> filters = new HashMap<>();
        filters.put("created_by", CREATED_BY_ID);
        JSONArray res = runs.get_runs(project_id,filters, OK);
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            Assert.assertNotNull(response.get("assignedto_id"));
            Assert.assertNotNull(response.get("blocked_count"));
            Assert.assertNull(response.get("completed_on"));
            Assert.assertEquals(response.get("created_by").toString(), "1");
            Assert.assertNotNull(response.get("created_on"));
            Assert.assertNotNull(response.get("include_all"));
            Assert.assertNotNull(response.get("is_completed"));
            Assert.assertNotNull(response.get("milestone_id"));
            Assert.assertNotNull(response.get("name"));
            Assert.assertNotNull(response.get("passed_count"));
            Assert.assertEquals(response.get("project_id").toString(), project_id);
            Assert.assertNotNull(response.get("suite_id").toString());
            Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
        }
    }
}
