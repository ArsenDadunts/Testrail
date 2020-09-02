package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.*;
import testrail.payloads.CaseFieldPayload;

import java.util.ArrayList;

import static testrail.utils.Utils_Constants.OK;

public class Case_Field {
    String project_id;
    Case_Fields case_fields = new Case_Fields();
    CaseFieldPayload payload = new CaseFieldPayload();
    @BeforeClass
    public void setup(){
        //get project
        Projects project = new Projects();
        JSONArray array = project.get_projects_with_filters(OK, 0);
        JSONObject jsonObject = (JSONObject) array.get(0);
        project_id = jsonObject.get("id").toString();
    }

    @Test
    public void get_case_fields() {
        JSONArray res = case_fields.get_case_fields(OK);
        for (Object re : res) {
            JSONObject object = (JSONObject) re;
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("name"));
        }
    }

    @Test
    public void add_case_field() {
        ArrayList<String> projectIds = new ArrayList<>();
        projectIds.add(project_id);
        payload.configs = payload.generateConfigs(true, projectIds,false, "1",
                "1, First\n2, Second");
        JSONObject response = case_fields.add_case_field(payload, OK);
        Assert.assertNotNull(response.get("id"));
        Assert.assertEquals(response.get("name").toString(), payload.name);
        Assert.assertNotNull(response.get("system_name"));
        Assert.assertEquals(response.get("description"), payload.description);
        Assert.assertEquals(response.get("label").toString(), payload.label);
        Assert.assertNotNull(response.get("type_id"));
        Assert.assertNotNull(response.get("location_id"));
        Assert.assertNotNull(response.get("display_order"));
        Assert.assertNotNull(response.get("is_multi"));
        Assert.assertNotNull(response.get("is_active"));
        Assert.assertNotNull(response.get("is_system"));
    }
}
