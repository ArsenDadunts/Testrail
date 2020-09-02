package testrail.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.Assert;
import testrail.common.Constants;
import testrail.executors.*;


public class Report {
    Reports reports = new Reports();

    @Test
    public void get_reports() {
        //get project
        Projects project = new Projects();
        JSONArray array = project.get_projects_with_filters(Constants.OK, 0);
        JSONObject jsonObject = (JSONObject) array.get(0);
        String project_id = jsonObject.get("id").toString();
        //get reports
        JSONArray res = reports.get_reports(project_id, Constants.OK);
        for (Object re : res) {
            JSONObject object = (JSONObject) re;
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("name"));
        }
    }

    @Test
    public void add_report() {
        JSONObject response = reports.run_report("1", Constants.OK);
        Assert.assertNotNull(response.get("report_url"));
        Assert.assertNotNull(response.get("report_html"));
        Assert.assertNotNull(response.get("report_pdf"));
    }
}
