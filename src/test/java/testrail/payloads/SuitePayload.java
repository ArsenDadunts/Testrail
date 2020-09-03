package testrail.payloads;

import static testrail.common.Utils.generateRandomInt;

public class SuitePayload {
    public String description = "description " + generateRandomInt();
    public String name = "Suite " + generateRandomInt();
}
