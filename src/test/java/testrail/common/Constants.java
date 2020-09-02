package testrail.common;

public class Constants {
    // response codes
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int FORBIDDEN = 403;
    //test result
    public static final int PASSED = 1;
    public static final int BLOCKED = 2;
    public static final int UNTESTED = 3;
    public static final int RETEST = 4;
    public static final int FAILED = 5;

    //ids
    public static final String EXAMPLE_TEST_ID = "1";
    public static final String ASSIGNED_TO_ID = "1";
    public static final String CREATED_BY_ID = "1";
    //example files
    public static final String RESOURCES_RESULTS_FOR_CASES_JSON = "src/test/resources/results_for_cases.json";
    public static final String RESOURCES_RESULTS_FOR_JSON = "src/test/resources/results.json";
    public static final String EXAMPLE_ATTACHMENT_FILE = "src/test/resources/images/test_image_1.jpg";
}
