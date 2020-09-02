package testrail.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.*;
import testrail.payloads.SuitePayload;


import static testrail.common.Constants.OK;

public class Suite {
    String project_id;
    Projects projects = new Projects();
    Suites suites = new Suites();
    SuitePayload payload = new SuitePayload();

    @BeforeClass
    public void setup() {
        JSONArray array = projects.get_projects_with_filters(OK, 0);
        JSONObject project = (JSONObject) array.get(0);
        project_id = project.get("id").toString();
    }

    @Test
    public void add_suite() {
        JSONObject response = suites.add_suite(project_id, payload, OK);
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertEquals(response.get("description").toString(), payload.description);
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertFalse(Boolean.parseBoolean(response.get("is_master").toString()));
        Assert.assertFalse(Boolean.parseBoolean(response.get("is_completed").toString()));
        Assert.assertNull(response.get("completed_on"));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void update_suite() {
        JSONObject res = suites.add_suite(project_id, payload, OK);
        String suite_id = res.get("id").toString();
        payload.name = "Updated suite name";
        payload.description = "Updated suite description";
        JSONObject response = suites.update_suite(suite_id, payload, OK);
        Assert.assertEquals(response.get("id").toString(), suite_id);
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertEquals(response.get("description").toString(), payload.description);
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertFalse(Boolean.parseBoolean(response.get("is_master").toString()));
        Assert.assertFalse(Boolean.parseBoolean(response.get("is_completed").toString()));
        Assert.assertNull(response.get("completed_on"));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void delete_suite() {
        JSONObject res = suites.add_suite(project_id, payload, OK);
        String suite_id = res.get("id").toString();
        suites.delete_suite(suite_id, OK);
    }

    @Test
    public void get_suite() {
        JSONObject res = suites.add_suite(project_id, payload, OK);
        String suite_id = res.get("id").toString();
        JSONObject response = suites.get_suite(suite_id, OK);
        Assert.assertEquals(response.get("id").toString(), suite_id);
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertEquals(response.get("description").toString(), payload.description);
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertFalse(Boolean.parseBoolean(response.get("is_master").toString()));
        Assert.assertFalse(Boolean.parseBoolean(response.get("is_completed").toString()));
        Assert.assertNull(response.get("completed_on"));
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void get_suites() {
        JSONArray res = suites.get_suites(project_id, OK);
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            Assert.assertNotNull(response.get("id"));
            Assert.assertNotNull(response.get("name"));
            Assert.assertEquals(response.get("project_id").toString(), project_id);
            Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
        }
    }
}
