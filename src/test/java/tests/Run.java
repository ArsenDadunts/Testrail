package tests;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;
import testrail.executors.Case;
import testrail.payloads.RunPayload;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static testrail.utils.Utils_Constants.*;

public class Run {
    testrail.executors.Run authentication = new testrail.executors.Run();
    Case aCase = new Case();
    RunPayload payload = new RunPayload();

    @Test()
    public void getRun() {
        authentication.get_run("2", OK);
    }

    @Test()
    public void getRuns() {
        authentication.get_runs("1", OK);
    }

    @Test()
    public void addRun() {

        ArrayList<Integer> caseIds = new ArrayList<Integer>();
        for (int i = 1; i < 100; i++) {
            caseIds.add(i);
        }

        authentication.add_run("1", payload.addRunData(caseIds), OK);
    }

    @Test()
    public void updateRun() {
        authentication.update_run("1", payload.updateRunData(1, "1m 30s"), OK);
    }

    @Test()
    public void deleteRun() {
        authentication.delete_run("1", OK);
    }
}
