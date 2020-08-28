package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.Cases;
import testrail.executors.Projects;
import testrail.payloads.CasePayload;

import static testrail.utils.Utils_Constants.*;


public class Case {
    String project_id;
    Projects project;
    Cases cases = new Cases();
    CasePayload payload = new CasePayload();

    @BeforeClass
    public void setup(){
        project= new Projects();
        JSONArray projects = project.get_projects_with_filters(200, 0);
        JSONObject project = (JSONObject) projects.get(0);
        project_id = project.get("id").toString();

    }

    @Test
    public void add_case(){
//        JSONObject response = cases.add_case()
    }

    @Test
    public void get_cases(){
        JSONArray response = cases.get_cases(project_id, 200);
    }
}
