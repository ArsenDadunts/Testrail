package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.executors.*;

import static testrail.utils.Utils_Constants.OK;

public class Template {
    Projects projects = new Projects();
    Templates templates = new Templates();

    @Test
    public void get_templates() {
        JSONArray project = projects.get_projects_with_filters(OK, 0);
        JSONObject project1 = (JSONObject) project.get(0);
        String project_id = project1.get("id").toString();
        JSONArray res = templates.get_templates(project_id, OK);
        for (Object re : res) {
            JSONObject object = (JSONObject) re;
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("name"));
            Assert.assertNotNull(object.get("is_default"));
        }
    }
}
