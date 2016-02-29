package tests;

import apiObjects.Projects;
import apiObjects.Sections;
import apiObjects.Suites;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SectionsTest extends ApiTest {

    Sections sections = new Sections(client);
    private static TestSetUp testSetUp = new TestSetUp();
    private static Projects projects = new Projects(client);
    private static Suites suites = new Suites(client);
    private static Long projectId;
    private static Long suiteId;
    private JSONObject sectionOne;
    private Long sectionId;

    @BeforeClass
    public static void sectionsSetup() throws APIException, IOException {

        JSONObject project =  projects.addProject(TestData.projectWithMultipleSuites);
        projectId = (Long) project.get("id");
        JSONObject suite = suites.addSuite(TestData.firstSuiteMap, projectId);
        suiteId = (Long) suite.get("id");
    }

    @Before
    public void testSetUp() throws APIException, IOException {

        sectionOne = sections.addSection(TestData.createSectionOne(suiteId),projectId);
        sectionId = (Long) sectionOne.get("id");
    }

    @After
    public void sectionsTearDown() throws IOException, APIException, InterruptedException {

        Thread.sleep(1000);
        testSetUp.cleanUpSections(projectId, suiteId);
    }

    @AfterClass
    public static void testTearDown() throws APIException, IOException {
        testSetUp.cleanUp();
    }

    @Test
    public void testGetSection() throws APIException, IOException, InterruptedException {


        JSONObject sectionFromResponse = sections.getSection(sectionId);
        assertEquals(sectionOne, sectionFromResponse);
    }

    @Test
    public void testGetSections() throws APIException, IOException, InterruptedException {

        JSONObject sectionTwo = sections.addSection(TestData.createSectionForSecondSuite(suiteId),projectId);
        JSONArray actualSections = sections.getSections(projectId, suiteId);
        assertTrue(actualSections.contains(sectionOne));
        assertTrue(actualSections.contains(sectionTwo));
    }

    @Test
    public void testUpdateSection() throws APIException, IOException, InterruptedException {

        sections.updateSection(TestData.sectionForUpdate, sectionId);
        JSONObject updatedSection = sections.getSection(sectionId);
        assertEquals(TestData.sectionForUpdate.get("name"), updatedSection.get("name"));
    }

    @Test
    public void testDeleteSection() throws APIException, IOException, InterruptedException {

        sections.deleteSection(sectionId);
        JSONArray actualSections = sections.getSections(projectId, suiteId);
        int sectionCount = actualSections.size();
        for (int index = 0; index < sectionCount; index++) {
            JSONObject section = (JSONObject) actualSections.get(index);
            assertFalse(sectionId.equals(section.get("id")));
        }
    }
}