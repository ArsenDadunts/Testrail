package tests;

import org.testng.annotations.Test;
import testrail.payloads.CasePayload;


public class Case {
    testrail.executors.Case authentication = new testrail.executors.Case();
    CasePayload payload = new CasePayload();


    @Test()
    public void getCase() {
        authentication.get_case("2");
    }

    @Test()
    public void getCases() {
        authentication.get_cases("1");
    }

    @Test()
    public void addCase() {
        for (int i = 0; i < 5; i++) {
            authentication.add_case( "1", payload.addCaseData());
        }
    }

    @Test()
    public void updateCase() {
        authentication.update_case( "1", payload.updateCaseData(1, "1m 30s"));
    }

    @Test()
    public void deleteCase() {
        authentication.delete_case("1");
    }
}
