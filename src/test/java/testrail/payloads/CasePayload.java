package testrail.payloads;

import java.util.*;

import static testrail.common.Utils.*;

@SuppressWarnings("rawtypes")
public class CasePayload {
    public String title = generateString();
    public int type_id = 1;
    public int template_id = 2;
    public int priority_id = 3;
    public String estimate = "30s";
    List custom_steps_separated = createSteps();
    public int milestone_id = 1;
    public String date = getDate();

    public Object updateCase(int priority_id, String estimate) {
        Map payload = convertObjectToMap(new UpdateCase());
        payload.put("priority_id", priority_id);
        payload.put("estimate", estimate);
        return payload;
    }

    public static List createSteps() {
        Map step1 = new HashMap();
        step1.put("content", "Step description 1");
        step1.put("expected", "Expected result 1");
        List steps = new ArrayList();
        steps.add(step1);

        return steps;
    }
}

class UpdateCase extends CasePayload {
    public int priority_id;
    public String estimate;
}
