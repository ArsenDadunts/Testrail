package testrail.payloads;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ResultPayload extends JSONObject {
    ObjectMapper mapper = new ObjectMapper();

    public Map addResultData(int status_id, String comment, String version, String defects, String assignedto_id) {
        Map map = new HashMap();
        map.put("status_id", status_id);
        map.put("comment", comment);
        map.put("version", version);
        map.put("elapsed", "30s");
        map.put("defects", defects);
        map.put("assignedto_id", assignedto_id);
        return map;
    }

    public Map updateResultData(int priority_id, String estimate) {
        Map map = new HashMap();
        map.put("priority_id", priority_id);
        map.put("estimate", estimate);
        return map;
    }

    public Map addResults(String file){
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(new File(file), new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
