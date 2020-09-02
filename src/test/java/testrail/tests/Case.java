package testrail.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.common.Constants;
import testrail.executors.*;
import testrail.payloads.CasePayload;


public class Case {
    String project_id, section_id;
    Projects project;
    Sections section;
    Cases cases = new Cases();
    CasePayload payload = new CasePayload();

    @BeforeClass
    public void setup() {
        project = new Projects();
        section = new Sections();
        //get projects
        JSONArray projects = project.get_projects_with_filters(200, 0);
        JSONObject project = (JSONObject) projects.get(0);
        project_id = project.get("id").toString();
        //get sections
        JSONArray res = section.get_sections(project_id, 200);
        JSONObject section = (JSONObject) res.get(0);
        section_id = section.get("id").toString();
    }

    @Test
    public void add_case() {
        //create case
        JSONObject response = cases.add_case(section_id, payload, Constants.OK);
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("title"), payload.title);
        Assert.assertEquals(response.get("section_id").toString(), section_id);
        Assert.assertNotNull(response.get("template_id"));
        Assert.assertNotNull(response.get("type_id"));
        Assert.assertNotNull(response.get("priority_id"));
        Assert.assertNull(response.get("milestone_id"));
        Assert.assertNull(response.get("refs"));
        Assert.assertEquals(response.get("created_by").toString(), "1");
        Assert.assertNotNull(response.get("created_on"));
        Assert.assertEquals(response.get("updated_by").toString(), "1");
        Assert.assertNotNull(response.get("updated_on"));
        Assert.assertEquals(response.get("estimate"), payload.estimate);
        Assert.assertNotNull(response.get("created_on"));
        Assert.assertNotNull(response.get("suite_id"));
        Assert.assertNotNull(response.get("display_order"));
    }

    @Test
    public void update_case() {
        //create case
        JSONObject response = cases.add_case(section_id, payload, Constants.OK);
        Assert.assertNotNull(response.get("id"));
        String case_id = response.get("id").toString();
        //update case
        JSONObject res = cases.update_case(case_id, payload.updateCase(2, "5m"), Constants.OK);
        Assert.assertEquals(res.get("id").toString(), case_id);
        Assert.assertNotNull(res.get("title"));
        Assert.assertEquals(res.get("section_id").toString(), section_id);
        Assert.assertNotNull(res.get("template_id"));
        Assert.assertNotNull(res.get("type_id"));
        Assert.assertEquals(res.get("priority_id").toString(), "2");
        Assert.assertNull(res.get("milestone_id"));
        Assert.assertNull(res.get("refs"));
        Assert.assertEquals(res.get("created_by").toString(), "1");
        Assert.assertNotNull(res.get("created_on"));
        Assert.assertEquals(res.get("updated_by").toString(), "1");
        Assert.assertNotNull(res.get("updated_on"));
        Assert.assertEquals(res.get("estimate"), "5m");
        Assert.assertNotNull(res.get("created_on"));
        Assert.assertNotNull(res.get("suite_id"));
        Assert.assertNotNull(res.get("display_order"));
    }

    @Test
    public void delete_case() {
        //create case
        JSONObject response = cases.add_case(section_id, payload, Constants.OK);
        Assert.assertNotNull(response.get("id"));
        String case_id = response.get("id").toString();
        //delete case
        cases.delete_case(case_id, Constants.OK);

    }

    @Test
    public void get_case(){
        //create case
        JSONObject response = cases.add_case(section_id, payload, Constants.OK);
        Assert.assertNotNull(response.get("id"));
        String case_id = response.get("id").toString();
        //get case
        JSONObject res = cases.get_case(case_id, Constants.OK);
        Assert.assertEquals(res.get("id").toString(), case_id);
        Assert.assertNotNull(res.get("title"));
        Assert.assertEquals(res.get("section_id").toString(), section_id);
        Assert.assertNotNull(res.get("template_id"));
        Assert.assertNotNull(res.get("type_id"));
        Assert.assertNotNull(res.get("priority_id"));
        Assert.assertNull(res.get("milestone_id"));
        Assert.assertNull(res.get("refs"));
        Assert.assertEquals(res.get("created_by").toString(), "1");
        Assert.assertNotNull(res.get("created_on"));
        Assert.assertEquals(res.get("updated_by").toString(), "1");
        Assert.assertNotNull(res.get("updated_on"));
        Assert.assertEquals(res.get("estimate"), "30s");
        Assert.assertNotNull(res.get("created_on"));
        Assert.assertNotNull(res.get("suite_id"));
        Assert.assertNotNull(res.get("display_order"));
    }


    @Test
    public void get_cases() {
        //get cases
        JSONArray response = cases.get_cases(project_id, Constants.OK);
        for (Object object : response) {
            JSONObject res = (JSONObject) object;
            Assert.assertNotNull(res.get("id"));
            Assert.assertNotNull(res.get("title"));
            Assert.assertNotNull(res.get("section_id"));
            Assert.assertNotNull(res.get("template_id"));
            Assert.assertNotNull(res.get("type_id"));
            Assert.assertNotNull(res.get("priority_id"));
            Assert.assertNull(res.get("milestone_id"));
            Assert.assertNull(res.get("refs"));
            Assert.assertNotNull(res.get("created_by"));
            Assert.assertNotNull(res.get("created_on"));
            Assert.assertNotNull(res.get("updated_by"));
            Assert.assertNotNull(res.get("updated_on"));
            Assert.assertNotNull(res.get("suite_id"));
            Assert.assertNotNull(res.get("display_order"));
        }
    }
}
