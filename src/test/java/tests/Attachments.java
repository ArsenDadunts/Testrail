package tests;

import org.testng.annotations.Test;
import testrail.executors.Attachment;

import static testrail.utils.Utils_Constants.*;

public class Attachments {
    Attachment attachments = new Attachment();


    @Test
    public void add_attachment_to_plan() {
        attachments.add_attachment_to_plan("3", OK);
    }
}
