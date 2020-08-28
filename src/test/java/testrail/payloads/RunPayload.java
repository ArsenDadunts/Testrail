package testrail.payloads;

import org.json.simple.JSONObject;
import testrail.utils.Utils_Constants;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RunPayload extends JSONObject {
    public String template_id = "TestRail 5.2";

    public Map addRunData(ArrayList case_ids) {
        Map map = new HashMap();
        map.put("suite_id", 1);
        map.put("run ", "Run"+ Utils_Constants.generateRandomInt());
        map.put("description", "Description"+Utils_Constants.generateRandomInt());
//        map.put("milestone_id", 1);
        map.put("assignedto_id", 1);
        map.put("include_all", true);
        map.put("case_ids", case_ids);
        map.put("refs", "SAN-1");
        return map;
    }

    public Map updateRunData(int priority_id, String estimate) {
        Map map = new HashMap();
        map.put("priority_id", priority_id);
        map.put("estimate", estimate);
        return map;
    }
}
