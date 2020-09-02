package testrail.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import testrail.executors.*;

import static testrail.common.Constants.OK;

public class Priority {
    Priorities priorities = new Priorities();

    @Test
    public void get_priorities() {
        JSONArray res = priorities.get_priorities(OK);
        for (Object re : res) {
            JSONObject object = (JSONObject) re;
            Assert.assertNotNull(object.get("id"));
            Assert.assertNotNull(object.get("name"));
            Assert.assertNotNull(object.get("short_name"));
            Assert.assertNotNull(object.get("is_default"));
            Assert.assertNotNull(object.get("priority"));
        }
    }
}
