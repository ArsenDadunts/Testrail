package tests;

import org.testng.annotations.Test;

import static testrail.utils.Utils_Constants.OK;

public class Template {
    testrail.executors.Templates authentication = new testrail.executors.Templates();

    @Test()
    public void getCases() {
        authentication.get_templates("1", OK);
    }
}
