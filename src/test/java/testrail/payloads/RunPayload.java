package testrail.payloads;

import org.json.simple.JSONObject;

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
        map.put("name", generateTitle());
        map.put("description", generateTitle());
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

    public static String generateTitle() {
        return "Example test Run " + new Random().nextInt(999999999);
    }

    public static int generateRandomInt() {
        return new Random().nextInt(65535);
    }

    public static String getDate() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ZonedDateTime.now().format(formatter2);
    }
}
