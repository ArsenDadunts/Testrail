package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.executors.Projects;
import testrail.payloads.ProjectPayload;

import java.util.HashMap;
import java.util.Map;

import static testrail.utils.Utils_Constants.*;

public class Project {
    Projects projects = new Projects();
    ProjectPayload payload = new ProjectPayload();

    @Test
    public void add_project() {
        JSONObject response = projects.add_project(payload, OK);
        Assert.assertEquals(response.get("announcement"), payload.announcement);
        Assert.assertNull(response.get("completed_on"));
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("is_completed"), false);
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("show_announcement"), payload.show_announcement);
        Assert.assertNotNull(response.get("url"));
    }
    @Test
    public void update_project() {
        JSONObject response = projects.add_project(payload, OK);
        Assert.assertEquals(response.get("announcement"), payload.announcement);
        Assert.assertNotNull(response.get("id"));
        String project_id = response.get("id").toString();
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("show_announcement"), payload.show_announcement);
        JSONObject res = projects.update_project(project_id, payload.updateProject(), OK);
        Assert.assertEquals(response.get("is_completed"), true);
        Assert.assertNotNull(res.get("completed_on"));
        Assert.assertNotNull(res.get("id"));
        Assert.assertEquals(res.get("name"), payload.name);
        Assert.assertEquals(res.get("show_announcement"), payload.show_announcement);
        Assert.assertNotNull(res.get("url"));
    }

    @Test
    public void delete_project(){
        JSONObject response = projects.add_project(payload, OK);
        Assert.assertEquals(response.get("announcement"), payload.announcement);
        Assert.assertNotNull(response.get("id"));
        String project_id = response.get("id").toString();
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("show_announcement"), payload.show_announcement);
        JSONObject res = projects.delete_project(project_id, OK);
    }

    @Test
    public void get_project(){
        JSONObject response = projects.add_project(payload, OK);
        Assert.assertNotNull(response.get("id"));
        String project_id = response.get("id").toString();
        JSONObject res = projects.get_project(project_id, OK);
        Assert.assertEquals(res.get("announcement"), payload.announcement);
        Assert.assertNull(res.get("completed_on"));
        Assert.assertNotNull(res.get("id"));
        Assert.assertEquals(res.get("is_completed"), false);
        Assert.assertEquals(res.get("name"), payload.name);
        Assert.assertEquals(res.get("show_announcement"), payload.show_announcement);
        Assert.assertNotNull(res.get("url"));
    }

    @Test
    public void get_projects_without_filters(){
        JSONArray res = projects.get_projects_without_filters(OK);
        for (int i = 0; i < res.size(); i++) {
            JSONObject object = (JSONObject) res.get(i);
            Assert.assertNotNull(object.get("announcement"));
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("is_completed"));
            Assert.assertNotNull(object.get("name"));
            Assert.assertNotNull(object.get("show_announcement"));
            Assert.assertNotNull(object.get("url"));
        }
    }

    @Test
    public void get_projects_with_filters(){
        JSONArray res = projects.get_projects_with_filters(OK, 0);
        for (int i = 0; i < res.size(); i++) {
            JSONObject object = (JSONObject) res.get(i);
            Assert.assertNotNull(object.get("announcement"));
            Assert.assertNotNull(object.get("id"));
            Assert.assertNull(object.get("completed_on"));
            Assert.assertEquals(object.get("is_completed"), false);
            Assert.assertNotNull(object.get("name"));
            Assert.assertNotNull(object.get("show_announcement"));
            Assert.assertNotNull(object.get("url"));
        }
    }
}
