package testrail.payloads;

import static testrail.utils.Utils_Constants.generateRandomInt;

public class SuitePayload {
    public String description = "description "+ generateRandomInt();
    public String name = "Suite " + generateRandomInt();
}
