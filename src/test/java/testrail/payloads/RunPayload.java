package testrail.payloads;

import java.util.ArrayList;
import java.util.Map;

import static testrail.common.Utils.*;

public class RunPayload {
    public int suite_id = 1;
    public String name = "Run " + generateRandomInt();
    public String description = "Description " + generateRandomInt();
    public int milestone_id = 8;
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
