package tests;

import apiObjects.*;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RunsTest extends ApiTest {

    public Runs runs = new Runs(client);
    private static TestSetUp testSetUp = new TestSetUp();
    private static Projects projects = new Projects(client);
    private static Suites suites = new Suites(client);
    private static Sections sections = new Sections(client);
    private static Cases cases = new Cases(client);
    private static Long projectId;
    private static Long suiteId;
    private static Long sectionId;
    private Long runId;
    private JSONObject firstRun;

    @Before
    public void setUpRun() throws APIException, IOException, InterruptedException {

        JSONObject project =  projects.addProject(TestData.projectWithMultipleSuites);
        projectId = (Long) project.get("id");
        JSONObject suite = suites.addSuite(TestData.firstSuiteMap, projectId);
        suiteId = (Long) suite.get("id");
        JSONObject sectionOne = sections.addSection(TestData.createSectionOne(suiteId),projectId);
        sectionId = (Long) sectionOne.get("id");

        firstRun = runs.addRun(TestData.createFirstRunMap(suiteId), projectId);
        runId = (Long) firstRun.get("id");
    }


    @After
    public void tearDownRun() throws APIException, IOException, InterruptedException {

        Thread.sleep(1000);
        testSetUp.cleanUp();
    }

    @Test
    public void testGetRun() throws APIException, IOException, InterruptedException {

        JSONObject actualRun = runs.getRun(runId);
        assertEquals(firstRun,actualRun);
    }

    @Test
    public void testGetRuns() throws APIException, IOException, InterruptedException {

        JSONObject secondSuite =  suites.addSuite(TestData.secondSuiteMap, projectId);
        Long secondSuiteId = (Long) secondSuite.get("id");
        JSONObject secondRun = runs.addRun(TestData.createSecondRunMap(secondSuiteId), projectId);
        JSONArray actualRuns =  runs.getRuns(projectId);
        assertTrue(actualRuns.contains(firstRun));
        assertTrue(actualRuns.contains(secondRun));
    }

    /*@Test
    public void testAddRun() throws APIException, IOException {

    }*/

    @Test
    public void testUpdateRun() throws APIException, IOException, InterruptedException {

        runs.updateRun(TestData.runForUpdate, runId);
        JSONObject updatedRun = runs.getRun(runId);
        assertEquals(TestData.runForUpdate.get("description"),updatedRun.get("description"));
        
    }

    @Test
    public void testCloseRun() throws APIException, IOException, InterruptedException {

        runs.closeRun(runId);
        JSONObject closedRun = runs.getRun(runId);
        assertEquals(closedRun.get("is_completed"), true);
    }

    @Test
    public void testDeleteRun() throws APIException, IOException, InterruptedException {

        runs.deleteRun(runId);
        JSONArray allRuns = runs.getRuns(projectId);
        int runsCount = allRuns.size();
        for (int index = 0; index < runsCount; index++) {
            JSONObject run = (JSONObject) allRuns.get(index);
            assertFalse(runId.equals(run.get("id")));
        }
    }
}