package testrail.payloads;
import java.util.Map;

import static testrail.utils.Utils_Constants.*;

public class MilestonePayload {
    public String description = "description "+ generateRandomInt();
    public String name = "Milestone " + generateRandomInt();
    public long due_on = setUnixTimeStamp(100);
    public long start_on = setUnixTimeStamp();

    public Object updateMilestone(boolean is_completed, boolean is_started){
        Map payload = convertObjectToMap(new UpdateCase());
        payload.put("is_completed", is_completed);
        payload.put("is_started", is_started);
        return payload;
    }
}

class UpdateMilestone extends MilestonePayload{
    public boolean is_completed;
    public boolean is_started;
}
