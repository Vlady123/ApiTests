package tests;

import apiObjects.*;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResultsTest extends ApiTest {

    private static TestSetUp testSetUp = new TestSetUp();
    private static Results results = new Results(client);
    private static Projects projects = new Projects(client);
    private static Suites suites = new Suites(client);
    private static Sections sections = new Sections(client);
    private static Cases cases = new Cases(client);
    private static Runs runs = new Runs(client);
    private static Tests tests = new Tests(client);
    private static Long projectId;
    private static Long suiteId;
    private static Long sectionId;
    private static Long firstCaseId;
    private static Long secondCaseId;
    private Long firstRunId;
    private Long firstTestId;
    private Long secondTestId;


    @BeforeClass
    public static void resultsSetup() throws APIException, IOException {
        JSONObject project =  projects.addProject(TestData.projectWithMultipleSuites);
        projectId = (Long) project.get("id");

        JSONObject suite = suites.addSuite(TestData.firstSuiteMap, projectId);
        suiteId = (Long) suite.get("id");

        JSONObject sectionOne = sections.addSection(TestData.createSectionOne(suiteId),projectId);
        sectionId = (Long) sectionOne.get("id");

        JSONObject firstCase = cases.addCase(TestData.testCase, sectionId);
        firstCaseId = (Long) firstCase.get("id");

        JSONObject secondCase = cases.addCase(TestData.firstCaseMap, sectionId);
        secondCaseId = (Long) secondCase.get("id");
    }

    @Before
    public void setUpRun() throws APIException, IOException {

        JSONObject firstRun = runs.addRun(TestData.createFirstRunMap(suiteId), projectId);
        firstRunId = (Long) firstRun.get("id");

        JSONArray testsArray = tests.getTests(firstRunId);

        JSONObject firstTest = (JSONObject) testsArray.get(0);
        firstTestId = (Long) firstTest.get("id");

        JSONObject secondTest = (JSONObject) testsArray.get(1);
        secondTestId = (Long) secondTest.get("id");
    }
    @After
    public void tearDownRun() throws APIException, IOException, InterruptedException {

        Thread.sleep(1000);
        testSetUp.cleanUpRuns(projectId);
    }

    @AfterClass
    public static void tearDownResults() throws APIException, IOException {

        testSetUp.cleanUp();
    }


    @Test
    public void testGetResults() throws APIException, IOException, InterruptedException {


        JSONObject result = results.addResult(TestData.firstTestResult, firstTestId);
        JSONArray firstTestResults = results.getResults(firstTestId);
        assertEquals(result, firstTestResults.get(0));
    }

    @Test
    public void testGetResultsForCase() throws APIException, IOException, InterruptedException {

        JSONObject resultsForFirstCase = results.addResultForCase(TestData.firstTestResult, firstRunId, firstCaseId);
        JSONObject actualResult = (JSONObject) results.getResultsForCase(firstRunId, firstCaseId).get(0);
        assertEquals(TestData.firstTestResult.get("status_id"), actualResult.get("status_id"));
        assertEquals(resultsForFirstCase,actualResult);
    }

    @Test
    public void testGetResultsForRun() throws APIException, IOException, InterruptedException {

        JSONArray expectedResults = results.addResults(TestData.createResultsForFirstRun(firstTestId, secondTestId), firstRunId);
        JSONArray actualResults = results.getResultsForRun(firstRunId);
        JSONObject firstResult = (JSONObject) expectedResults.get(0);
        JSONObject secondResult = (JSONObject) expectedResults.get(1);
        assertTrue(actualResults.contains(firstResult));
        assertTrue(actualResults.contains(secondResult));
    }

    @Test
    public void testAddResultsForCases() throws APIException, IOException {

        JSONArray expectedResults =  results.addResultsForCases(TestData.createResultsForCases(firstCaseId, secondCaseId), firstRunId);
        JSONArray resultsForCases = results.getResultsForRun(firstRunId);
        JSONObject firstResult = (JSONObject) expectedResults.get(0);
        JSONObject secondResult = (JSONObject) expectedResults.get(1);
        assertTrue(resultsForCases.contains(firstResult));
        assertTrue(resultsForCases.contains(secondResult));
    }
}