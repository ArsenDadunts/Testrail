package testrail.payloads;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static testrail.common.Utils.*;

public class CaseFieldPayload {
    public String type = "Multiselect";
    public String name = "my_multiselect";
    public String label = "My Multiselect";
    public String description = "description " + generateString();
    public boolean include_all = true;
    public Object configs = null;

    public JSONArray generateConfigs(boolean is_global, ArrayList project_ids, boolean is_required, String default_value,
                                     String items) {
        JSONArray array = new JSONArray();
        JSONObject context = new JSONObject();
        JSONObject options = new JSONObject();
        JSONObject configs = new JSONObject();
        context.put("is_global", is_global);
        context.put("project_ids", project_ids);
        options.put("is_required", is_required);
        options.put("items", items);
        configs.put("context", context);
        configs.put("options", options);
        array.add(configs);
        return array;
    }
}
