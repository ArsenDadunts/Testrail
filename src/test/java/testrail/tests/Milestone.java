package testrail.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.Milestones;
import testrail.executors.Projects;
import testrail.payloads.MilestonePayload;

import java.util.HashMap;

import static testrail.common.Constants.OK;

public class Milestone {
    String project_id;
    Projects projects = new Projects();
    Milestones milestones = new Milestones();
    MilestonePayload payload = new MilestonePayload();

    @BeforeClass
    public void setup(){
        JSONArray array = projects.get_projects_with_filters(OK,0);
        JSONObject project = (JSONObject) array.get(0);
        project_id = project.get("id").toString();
    }

    @Test
    public void add_milestone() {
        JSONObject response = milestones.add_milestone(project_id, payload, OK);
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertEquals(response.get("description").toString(), payload.description);
        Assert.assertEquals(Long.parseLong(response.get("due_on").toString()), payload.due_on);
        Assert.assertEquals(Long.parseLong(response.get("start_on").toString()), payload.start_on);
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void update_milestone(){
        JSONObject res = milestones.add_milestone(project_id, payload, OK);
        String milestone_id = res.get("id").toString();
        JSONObject response = milestones.update_milestone(milestone_id, payload.updateMilestone(true, true), OK);
        Assert.assertEquals(response.get("id").toString(), milestone_id);
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertEquals(response.get("description").toString(), payload.description);
        Assert.assertTrue(Boolean.parseBoolean(response.get("is_completed").toString()));
        Assert.assertTrue(Boolean.parseBoolean(response.get("is_started").toString()));
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void delete_milestone(){
        JSONObject res = milestones.add_milestone(project_id, payload, OK);
        String milestone_id = res.get("id").toString();
        milestones.delete_milestone(milestone_id, OK);
    }

    @Test
    public void get_milestone(){
        JSONObject res = milestones.add_milestone(project_id, payload, OK);
        String milestone_id = res.get("id").toString();
        JSONObject response = milestones.get_milestone(milestone_id, OK);
        Assert.assertEquals(response.get("id").toString(), milestone_id);
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertEquals(response.get("description").toString(), payload.description);
        Assert.assertEquals(Long.parseLong(response.get("due_on").toString()), payload.due_on);
        Assert.assertEquals(Long.parseLong(response.get("start_on").toString()), payload.start_on);
        Assert.assertEquals(response.get("project_id").toString(), project_id);
        Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
    }

    @Test
    public void get_milestones(){
        HashMap<String, Integer> filters = new HashMap<>();
        filters.put("is_completed", 0);
        filters.put("is_started", 0);
        JSONArray res = milestones.get_milestones(project_id, filters, OK);
        for (Object re : res) {
            JSONObject response = (JSONObject) re;
            Assert.assertNotNull(response.get("name"));
            Assert.assertNotNull(response.get("description"));
            Assert.assertNotNull(response.get("id"));
            Assert.assertEquals(response.get("project_id").toString(), project_id);
            Assert.assertTrue(response.get("url").toString().startsWith(System.getProperty("BASE_URL")));
        }
    }
}
