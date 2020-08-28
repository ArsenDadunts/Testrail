package tests;

import org.testng.annotations.Test;
import testrail.payloads.CasePayload;

import static testrail.utils.Utils_Constants.*;


public class Case {
    testrail.executors.Cases authentication = new testrail.executors.Cases();
    CasePayload payload = new CasePayload();


    @Test()
    public void getCase() {
        authentication.get_case("2", OK);
    }

    @Test()
    public void getCases() {
        authentication.get_cases("1", OK, null);
    }

    @Test()
    public void addCase() {
        for (int i = 0; i < 5; i++) {
            authentication.add_case("1", payload.addCaseData(), OK);
        }
    }

    @Test()
    public void updateCase() {
        authentication.update_case("1", payload.updateCaseData(1, "1m 30s"), OK);
    }

    @Test()
    public void deleteCase() {
        authentication.delete_case("1", OK);
    }
}
