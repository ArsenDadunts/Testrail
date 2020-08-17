package testrail.payloads;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ResultPayload extends JSONObject {
    public String template_id = "TestRail 5.2";

    public Map addResultData(String comment, String version, String defects, int assignedto_id) {
        Map map = new HashMap();
        map.put("status_id", 1);
        map.put("comment", comment);
        map.put("version", version);
        map.put("elapsed", "30s");
        map.put("defects", defects);
        map.put("assignedto_id", assignedto_id);
        return map;
    }

    public Map updateResultData(int priority_id, String estimate) {
        Map map = new HashMap();
        map.put("priority_id", priority_id);
        map.put("estimate", estimate);
        return map;
    }

    public static String generateTitle() {
        return "Example test " + new Random().nextInt(999999999);
    }

    public static int generateRandomInt() {
        return new Random().nextInt(65535);
    }

    public static String getDate() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ZonedDateTime.now().format(formatter2);
    }
}
