package testrail.payloads;

import testrail.utils.Utils_Constants;

import java.util.Random;

public class ProjectPayload {
    public String name = "Project Name "+ Utils_Constants.generateRandomInt();
    public  String announcement = "Description "+Utils_Constants.generateRandomInt();
    public  boolean show_announcement = true;
    public int suite_mode = new  Random().nextInt(4);
}
