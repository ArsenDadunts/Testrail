package tests;

import org.testng.annotations.Test;

import static testrail.utils.Utils_Constants.OK;

public class Template {
    testrail.executors.Template authentication = new testrail.executors.Template();

    @Test()
    public void getCases() {
        authentication.get_templates("1", OK);
    }
}
