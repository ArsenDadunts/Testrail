package testrail.payloads;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static testrail.utils.Utils_Constants.*;

public class RunPayload {
    public int suite_id = 1;
    public String name = "Run " + generateRandomInt();
    public String description = "Description " + generateRandomInt();
    public int milestone_id = 1;
    public int assignedto_id = 1;
    public boolean include_all = false;
    public ArrayList case_ids;

    public Object UpdateRun(int priority_id, String estimate) {
        Map payload = convertObjectToMap(new UpdateRun());
        payload.put("priority_id", priority_id);
        payload.put("estimate", estimate);
        return payload;
    }
}

class UpdateRun extends RunPayload {
}
