package tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.executors.*;

import static testrail.utils.Utils_Constants.OK;

public class Case_Type {
    Case_Types case_types = new Case_Types();

    @Test
    public void get_priorities() {
        JSONArray res = case_types.get_case_types(OK);
        for (Object re : res) {
            JSONObject object = (JSONObject) re;
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("name"));
            Assert.assertNotNull(object.get("is_default"));
        }
    }
}
