package testrail.payloads;

import testrail.common.Utils;

public class SectionPayload {
    public String description = "description " + Utils.generateRandomInt();
    public long suite_id;
    public String name = "Section Name " + Utils.generateRandomInt();
}