package testrail.payloads;

import testrail.utils.Utils_Constants;

public class SectionPayload {
    public String description = "description "+Utils_Constants.generateRandomInt();
    public int suite_id;
    public int parent_id;
    public String name = "Section Name" + Utils_Constants.generateRandomInt();
}