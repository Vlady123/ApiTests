package tests;

import apiObjects.Projects;
import apiObjects.Suites;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;


public class SuitesTest extends ApiTest {

    private static TestSetUp testSetUp = new TestSetUp();
    private static Projects projects = new Projects(client);
    protected Suites suites = new Suites(client);
    private static Long projectId;
    private JSONObject suite;
    private Long suiteId;


    @BeforeClass
    public static void suitesSetup() throws APIException, IOException {

        JSONObject project =  projects.addProject(TestData.projectWithMultipleSuites);
        projectId = (Long) project.get("id");
    }

    @Before
    public void testSetUp() throws APIException, IOException {

        suite = suites.addSuite(TestData.firstSuiteMap, projectId);
        suiteId = (Long) suite.get("id");
    }

    @After
    public void testTearDown() throws APIException, IOException, InterruptedException {

        Thread.sleep(1000);
        testSetUp.cleanUpSuites(projectId);
    }
    @AfterClass
    public static void suitesTearDown() throws APIException, IOException {
        testSetUp.cleanUp();
    }

    @Test
    public void testGetSuite() throws APIException, IOException, InterruptedException {

        JSONObject actualSuite =  suites.getSuite(suiteId);
        assertEquals(suite, actualSuite);
    }

    @Test
    public void testGetSuites() throws APIException, IOException, InterruptedException {

        JSONObject secondSuite = suites.addSuite(TestData.secondSuiteMap, projectId);
        JSONArray actualSuites = suites.getSuites(projectId);
        assertTrue(actualSuites.contains(suite));
        assertTrue(actualSuites.contains(secondSuite));
    }

    @Test
    public void testUpdateSuite() throws APIException, IOException, InterruptedException {

        suites.updateSuite(TestData.suiteForUpdate, suiteId);
        JSONObject expectedSuite = suites.getSuite(suiteId);
        assertEquals(expectedSuite.get("description"),TestData.suiteForUpdate.get("description"));
    }

    @Test
    public void testDeleteSuite() throws APIException, IOException, InterruptedException {

        suites.deleteSuite(suiteId);
        JSONArray actualSuites = suites.getSuites(projectId);
        int count = actualSuites.size();
        for (int index = 0; index < count; index++) {
            JSONObject suite = (JSONObject) actualSuites.get(index);
            assertFalse(suiteId.equals(suite.get("id")));
        }
    }
}