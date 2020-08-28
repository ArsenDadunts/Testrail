package testrail.payloads;

import org.json.simple.JSONObject;
import java.util.*;

import static testrail.utils.Utils_Constants.*;

public class CasePayload extends JSONObject {
    public String template_id = "TestRail 5.2";

    public Map addCaseData() {
        Map map = new HashMap();
        map.put("title", generateTitle());
//        map.put("template_id", template_id);
        map.put("type_id", 1);
        map.put("priority_id", 3);
        map.put("estimate", "30s");
        map.put("Steps", createSteps());
        map.put("milestone_id", 1);
        map.put("date", getDate());
        return map;
    }

    public Map updateCaseData(int priority_id, String estimate) {
        Map map = new HashMap();
        map.put("priority_id", priority_id);
        map.put("estimate", estimate);
        return map;
    }

    public static Map createSteps() {
        Map step1 = new HashMap();
        step1.put("status_id", new Integer(5));
        step1.put("content", "Step description 1");
        step1.put("expected", "Expected result 1");
        step1.put("actual", "Actual result 1");
        List steps = new ArrayList();
        steps.add(step1);

        Map data = new HashMap();
        data.put("custom_steps_separated", steps);
        return data;
    }
}
