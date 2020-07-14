package tests;

import org.testng.annotations.Test;
import testrail.payloads.ResultPayload;


public class Results {
    testrail.executors.Results authentication = new testrail.executors.Results();
    ResultPayload payload = new ResultPayload();

    @Test()
    public void getResults() {
        authentication.get_results("1");
    }

    @Test()
    public void addResult() {
        authentication.add_result( "2", payload.addResultData("All works fine", "1.1.1", "", 1));
    }
}
