package testrail.payloads;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ResultPayload extends JSONObject {

    public Map addResultData(int status_id, String comment, String version, String defects, int assignedto_id) {
        Map map = new HashMap();
        map.put("status_id", status_id);
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
}
