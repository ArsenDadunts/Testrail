package testrail.payloads;

import java.util.Map;
import java.util.Random;

import static testrail.utils.Utils_Constants.*;

public class ProjectPayload {
    public String name = "Project Name " + generateRandomInt();
    public String announcement = "Description " + generateRandomInt();
    public boolean show_announcement = true;
    public int suite_mode = new Random().nextInt(3) + 1;

    public Object updateProject() {
        Map payload = convertObjectToMap(new Update());
        return payload;
    }
}

class Update extends ProjectPayload {
    public boolean is_completed = true;
}
