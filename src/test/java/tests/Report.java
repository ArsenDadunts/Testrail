package tests;

import org.testng.annotations.Test;
import testrail.executors.Result;
import testrail.payloads.ResultPayload;

import static testrail.utils.Utils_Constants.*;


public class Report {
    Result authentication = new Result();
    ResultPayload payload = new ResultPayload();

    @Test()
    public void getResults() {
        authentication.get_results("1", OK);
    }

    @Test()
    public void addResult() {
        authentication.add_result("2", payload.addResultData("All works fine", "1.1.1", "", 1), OK);
    }

    @Test
    public void upload(){

    }
}
