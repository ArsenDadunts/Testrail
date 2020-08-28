package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.Projects;
import testrail.executors.Sections;
import testrail.payloads.SectionPayload;

import static testrail.utils.Utils_Constants.OK;

public class Section {
    String project_id;
    long suite_id;
    String section_id;
    Projects project;
    Sections sections = new Sections();
    SectionPayload payload = new SectionPayload();

    @BeforeClass
    public void setup(){
        project= new Projects();
        JSONArray projects = project.get_projects_with_filters(OK, 0);
        JSONObject project = (JSONObject) projects.get(0);
        project_id = project.get("id").toString();
        suite_id = (long) project.get("suite_mode");
    }

    @Test
    public void add_section(){
        payload.suite_id = suite_id;
        JSONObject response = sections.add_section(project_id, payload, OK);
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("suite_id"), suite_id);
        Assert.assertEquals(response.get("name"), payload.name);
        Assert.assertEquals(response.get("parent_id"), null);
        Assert.assertNotNull(response.get("display_order"));
        Assert.assertNotNull(response.get("depth"));
        Assert.assertEquals(response.get("description"), payload.description);
    }

    @Test
    public void update_section(){
        payload.suite_id = suite_id;
        JSONObject response = sections.add_section(project_id, payload, OK);
        Assert.assertNotNull(response.get("id"));
        section_id = response.get("id").toString();
        JSONObject res = sections.update_section(section_id, payload, OK);
        Assert.assertEquals(res.get("id"), section_id);
        Assert.assertEquals(res.get("suite_id"), suite_id);
        Assert.assertEquals(res.get("name"), payload.name);
        Assert.assertEquals(res.get("parent_id"), null);
        Assert.assertNotNull(res.get("display_order"));
        Assert.assertNotNull(res.get("depth"));
        Assert.assertEquals(res.get("description"), payload.description);
    }

    @Test
    public void delete_section(){
        payload.suite_id = suite_id;
        JSONObject response = sections.add_section(project_id, payload, OK);
        Assert.assertNotNull(response.get("id"));
        section_id = response.get("id").toString();
        JSONObject res = sections.delete_section(section_id, OK);
    }

    @Test
    public void get_sections(){
       JSONArray res = sections.get_sections(project_id, OK);
        for (int i = 0; i < res.size(); i++) {
            JSONObject object = (JSONObject) res.get(i);
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("suite_id"));
            Assert.assertNotNull(object.get("name"));
            Assert.assertEquals(object.get("parent_id"), null);
            Assert.assertNotNull(object.get("display_order"));
            Assert.assertNotNull(object.get("depth"));
            Assert.assertNotNull(object.get("description"));
        }
    }
}
