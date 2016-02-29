package tests;

import apiObjects.Cases;
import apiObjects.Projects;
import apiObjects.Sections;
import apiObjects.Suites;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class CasesTest extends ApiTest {

    private static TestSetUp testSetUp = new TestSetUp();
    private static Projects projects = new Projects(client);
    private static Suites suites = new Suites(client);
    private static Sections sections = new Sections(client);
    private static Cases cases = new Cases(client);
    private static Long projectId;
    private static Long suiteId;
    private static Long sectionId;
    private JSONObject firstCase;
    private Long firstCaseId;


    @BeforeClass
    public static void casesSetup() throws APIException, IOException {

        JSONObject project =  projects.addProject(TestData.projectWithMultipleSuites);
        projectId = (Long) project.get("id");
        JSONObject suite = suites.addSuite(TestData.firstSuiteMap, projectId);
        suiteId = (Long) suite.get("id");
        JSONObject sectionOne = sections.addSection(TestData.createSectionOne(suiteId),projectId);
        sectionId = (Long) sectionOne.get("id");
    }

    @After
    public void casesTearDown() throws APIException, IOException, InterruptedException {

        Thread.sleep(1000);
        testSetUp.cleanUpCases(projectId, suiteId);
    }

    @Before
    public void testSetUp() throws APIException, IOException {

        firstCase = cases.addCase(TestData.testCase, sectionId);
        firstCaseId = (Long) firstCase.get("id");
    }

    @AfterClass
    public static void casesTestsTearDown() throws APIException, IOException {

        testSetUp.cleanUp();
    }

    @Test
    public void testGetCase() throws APIException, IOException, InterruptedException {

        JSONObject actualCase = cases.getCase(firstCaseId);
        assertEquals(firstCase, actualCase);
    }

    @Test
    public void testGetCases() throws APIException, IOException, InterruptedException {

        JSONObject secondCase = cases.addCase(TestData.secondTestCase, sectionId);
        JSONArray casesFromResponse = cases.getCases(projectId, suiteId);
        assertTrue(casesFromResponse.contains(firstCase));
        assertTrue(casesFromResponse.contains(secondCase));
    }

    @Test
    public void testAddCase() throws APIException, IOException {

    }

    @Test
    public void testUpdateCase() throws APIException, IOException, InterruptedException {


        JSONObject updatedCase = cases.updateCase(TestData.newData, firstCaseId);
        JSONObject actualCase = cases.getCase(firstCaseId);
        assertEquals(actualCase.get("priority_id").toString(),TestData.newData.get("priority_id").toString());
    }

    @Test
    public void testDeleteCase() throws APIException, IOException, InterruptedException {

        cases.deleteCase(firstCaseId);
        JSONArray casesFromResponse = cases.getCases(projectId, suiteId);
        int count = casesFromResponse.size();
        for (int index = 0; index < count; index++) {
            JSONObject caseFromArray = (JSONObject) casesFromResponse.get(index);
            assertFalse(firstCaseId.equals(caseFromArray.get("id")));
        }
    }
}