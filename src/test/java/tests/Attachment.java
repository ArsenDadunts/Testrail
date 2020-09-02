package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testrail.executors.*;

import java.util.HashMap;

import static testrail.utils.Utils_Constants.*;

public class Attachment {
    Attachments attachments = new Attachments();
    String project_id, run_id, result_id;
    testrail.executors.Tests tests = new testrail.executors.Tests();
    Runs run;
    Results results;
    Projects project;

    @BeforeClass
    public void setup() {
        run = new Runs();
        project = new Projects();
        JSONArray projects = project.get_projects_with_filters(OK, 0);
        JSONObject project = (JSONObject) projects.get(0);
        project_id = project.get("id").toString();
        //get runs
        HashMap<String, String> filters = new HashMap<>();
        filters.put("created_by", CREATED_BY_ID);
        JSONArray runs = run.get_runs(project_id, filters, OK);
        JSONObject run = (JSONObject) runs.get(0);
        run_id = run.get("id").toString();
        //get results
        JSONArray res = results.get_results_for_run(run_id,filters, OK);
        JSONObject object = (JSONObject) res.get(0);
        result_id = object.get("id").toString();

    }

    @Test
    public void add_attachment_to_result() {
        JSONObject res = attachments.add_attachment_to_result(result_id, EXAMPLE_ATTACHMENT_FILE, OK);
        Assert.assertNotNull(res.get("attachment_id"));
    }
}
