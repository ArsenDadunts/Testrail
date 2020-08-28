package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.executors.Projects;
import testrail.payloads.ProjectPayload;

import static testrail.utils.Utils_Constants.*;

public class Project {
    Projects projects = new Projects();
    ProjectPayload payload = new ProjectPayload();

    @Test
    public void add_project() {
        JSONObject response = (JSONObject) projects.add_project(payload, OK);
        Assert.assertEquals(response.get("announcement"), payload.announcement);
        Assert.assertNull(response.get("completed_on"));
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("is_completed"), false);
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("show_announcement"), payload.show_announcement);
        Assert.assertNotNull(response.get("url"));
    }
}
