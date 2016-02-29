package tests;

import apiObjects.Projects;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProjectsTest extends ApiTest {


    private static TestSetUp testSetUp = new TestSetUp();
    private Projects projects = new Projects(client);
    private JSONObject firstProject;
    private Long firstProjectId;

    @Before
    public void testSetUp() throws APIException, IOException {

        firstProject = projects.addProject(TestData.projectForAdd);
        firstProjectId = (Long) firstProject.get("id");
    }


    @After
    public void tearDown() throws APIException, IOException, InterruptedException {

        Thread.sleep(1000);
        testSetUp.cleanUp();
    }

    @Test
    public void testGetProject() throws APIException, IOException, InterruptedException {

        JSONObject actualProject = projects.getProject(firstProjectId);
        assertEquals(firstProject, actualProject);
    }

    @Test
    public void testGetProjects() throws APIException, IOException, InterruptedException {

        JSONObject secondProject = projects.addProject(TestData.secondProjectMap);
        JSONArray projectsFromResponse = projects.getProjects();
        assertTrue(projectsFromResponse.contains(firstProject));
        assertTrue(projectsFromResponse.contains(secondProject));
    }

    @Test
    public void testAddProject() throws APIException, IOException {

    }

    @Test
    public void testUpdateProject() throws APIException, IOException, InterruptedException {

        JSONObject updatedProject = projects.updateProject(TestData.completeProject, firstProjectId);
        assertTrue((Boolean) updatedProject.get("is_completed"));
    }

    @Test
    public void testDeleteProject() throws APIException, IOException, InterruptedException {

        projects.deleteProject(firstProjectId);
        JSONArray actualProjects = projects.getProjects();
        for (int index = 0; index < actualProjects.size(); index++) {
            JSONObject project = (JSONObject) actualProjects.get(index);
            assertFalse(project.get("id").equals(firstProjectId));
        }
    }
}