package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir.
 */
public class TestData {

    public static Map secondProjectMap = new HashMap(){{

        put("name", "SecondProject");
        put("announcement", "Announcement for Second Project");
        put("show_announcement", true);
        put("suite_mode", 2);

    }};
    public static Map projectWithMultipleSuites = new HashMap(){{

        put("name", "TestProject");
        put("announcement", "Announcement for TestProject");
        put("show_announcement", true);
        put("suite_mode", 3);

    }};

    public static Map projectForAdd = new HashMap(){{

        put("name", "ProjectForAdd");
        put("announcement", "Announcement for ProjectForAdd");
        put("show_announcement", true);
        put("suite_mode", 3);

    }};

    public static Map completeProject = new HashMap(){{

        put("is_completed", true);
    }};

    public static Map firstCaseMap = new HashMap(){{

        put("title", "FirstTestCase");
        put("template_id", 1);
        put("type_id", 1);
        put("priority_id", 1);
        put("estimate", "30s");
        put("refs","RF-1, RF-2");
    }};

    public static Map firstSuiteMap = new HashMap(){{

        put("name", "Version 1.0");
        put("description", "Base version");
    }};

    public static Map secondSuiteMap = new HashMap(){{

        put("name", "Version 2.0");
        put("description", "new version");
    }};


    public static Map secondTestCase = new HashMap(){{
            put("title", "First Case");
            put("template_id", 1);
            put("type_id", 3);
            put("priority_id", 3);
            put("estimate", "1m");
            put("refs", "RF-3, RF-4");
            put("custom_steps", "step to execute");
            put("custom_expected", "Expected Result");
        }};

    public static Map newData = new HashMap(){{
        put("priority_id", 3);
    }};


    public static Map runForUpdate = new HashMap(){{
        put("description", "updated");
    }};
    public static Map suiteForUpdate = new HashMap(){{
        put("description", "new description");
    }};
    public static Map sectionForUpdate = new HashMap(){{
        put("name", "new name");
    }};
    public static Map firstTestResult = new HashMap(){{
        put("status_id", 1L);
        put("comment", "Test Passed");
    }};

    public static Map createSectionOne(Long suiteId){
        HashMap sectionOne = new HashMap();
        sectionOne.put("description", "description for section");
        sectionOne.put("name", "Section One");
        sectionOne.put("suite_id", suiteId);
        return sectionOne;
    }

    public static Map testCase = new HashMap(){{
        put("title", "Case");
        put("template_id", 1);
        put("type_id", 3);
        put("priority_id", 2);
        put("estimate", "1m");
        put("refs", "RF-1, RF-2");
        put("custom_steps", "step to execute");
        put("custom_expected", "Expected Result");
    }};

    public static Map createFirstRunMap(Long suiteId) {
        HashMap FirstRunMap = new HashMap();
        FirstRunMap.put("suite_id", suiteId);
        FirstRunMap.put("name", "FirstRun");
        FirstRunMap.put("description", "The description of the test FirstRun");
        FirstRunMap.put("include_all", true);
        return FirstRunMap;
    }

    public static Map createSecondRunMap(Long suiteId) {
        HashMap SecondRunMap = new HashMap();
        SecondRunMap.put("suite_id", suiteId);
        SecondRunMap.put("name", "SecondRun");
        SecondRunMap.put("description", "The description of the test SecondRun");
        SecondRunMap.put("include_all", true);
        return SecondRunMap;
    }

    public static Map createSectionForSecondSuite(Long suiteId) {

        HashMap sectionForSecondSuite = new HashMap();
        sectionForSecondSuite.put("description", "description for section in" + suiteId);
        sectionForSecondSuite.put("name", "Section in "+ suiteId);
        sectionForSecondSuite.put("suite_id", suiteId);
        return sectionForSecondSuite;
    }

    public static Map createResultsForFirstRun(Long firstTestId, Long secondTestId) {
        HashMap firstTestResult = new HashMap();
        firstTestResult.put("test_id", firstTestId);
        firstTestResult.put("version", "1.0");
        firstTestResult.put("status_id", 1);
        firstTestResult.put("comment", "Comment for first test");
        firstTestResult.put("elapsed", "10s");

        HashMap secondTestResult = new HashMap();
        secondTestResult.put("test_id", secondTestId);
        secondTestResult.put("version", "1.0");
        secondTestResult.put("status_id", 5);
        secondTestResult.put("comment", "Comment for second test");
        secondTestResult.put("elapsed", "10s");

        ArrayList resultsList = new ArrayList();
        resultsList.add(firstTestResult);
        resultsList.add(secondTestResult);
        HashMap results = new HashMap();
        results.put("results", resultsList);
        return results;
    }

    public static Map createResultsForCases(Long firstCaseId, Long secondCaseId) {
        HashMap firstTestResult = new HashMap();
        firstTestResult.put("case_id", firstCaseId);
        firstTestResult.put("version", "1.0");
        firstTestResult.put("status_id", 2);
        firstTestResult.put("comment", "Comment for first test");
        firstTestResult.put("elapsed", "10s");

        HashMap secondTestResult = new HashMap();
        secondTestResult.put("case_id", secondCaseId);
        secondTestResult.put("version", "1.0");
        secondTestResult.put("status_id", 4);
        secondTestResult.put("comment", "Comment for second test");
        secondTestResult.put("elapsed", "10s");

        ArrayList resultsList = new ArrayList();
        resultsList.add(firstTestResult);
        resultsList.add(secondTestResult);
        HashMap results = new HashMap();
        results.put("results", resultsList);
        return results;
    }
}
