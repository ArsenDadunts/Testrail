package tests;

import org.testng.annotations.Test;

import static testrail.utils.Utils_Constants.*;

public class Attachment {
    testrail.executors.Attachments attachments = new testrail.executors.Attachments();


    @Test
    public void add_attachment_to_plan() {
        attachments.add_attachment_to_plan("3", OK);
    }
}
