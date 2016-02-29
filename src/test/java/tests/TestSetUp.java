package tests;

import apiObjects.*;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by Vladimir.
 */
public class TestSetUp extends ApiTest {

    private static Cases cases = new Cases(client);
    private Runs runs = new Runs(client);
    private static Sections sections = new Sections(client);
    private static Projects projects = new Projects(client);
    private static Suites suites = new Suites(client);

    public void cleanUp() throws APIException, IOException {

        JSONArray projectsFromResponse = projects.getProjects();

        int projectCount = projectsFromResponse.size();

        if (projectCount != 0) {
            for (int i = 0; i < projectCount; i++) {
                JSONObject project = (JSONObject) projectsFromResponse.get(i);
                Long projectId = (Long) project.get("id");
                projects.deleteProject(projectId);
            }
        }
    }

    public void cleanUpSuites(Long projectId) throws APIException, IOException {


        JSONArray actualSuites = suites.getSuites(projectId);
        int count = actualSuites.size();
        for (int index = 0; index < count; index++) {
            JSONObject suite = (JSONObject) actualSuites.get(index);
            Long suiteId = (Long) suite.get("id");
            suites.deleteSuite(suiteId);
        }
    }

    public void cleanUpSections(Long projectId, Long suiteId) throws IOException, APIException {

        JSONArray actualSections = sections.getSections(projectId, suiteId);
        int count = actualSections.size();
        for (int index = 0; index < count; index++) {
            JSONObject section = (JSONObject) actualSections.get(index);
            Long sectionId = (Long) section.get("id");
            sections.deleteSection(sectionId);
        }
    }

    public void cleanUpCases(Long projectId, Long suiteId) throws APIException, IOException {

        JSONArray casesFromResponse = cases.getCases(projectId, suiteId);
        int count = casesFromResponse.size();
        for (int index = 0; index < count; index++) {
            JSONObject caseFromArray = (JSONObject) casesFromResponse.get(index);
            Long caseId  = (Long) caseFromArray.get("id");
            cases.deleteCase(caseId);
        }
    }

    public void cleanUpRuns(Long projectId) throws APIException, IOException {

        JSONArray allRuns = runs.getRuns(projectId);
        int runsCount = allRuns.size();
        for (int index = 0; index < runsCount; index++) {
            JSONObject run = (JSONObject) allRuns.get(index);
            Long runId = (Long) run.get("id");
            runs.deleteRun(runId);
        }
    }
}