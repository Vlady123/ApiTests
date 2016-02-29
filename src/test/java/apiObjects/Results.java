package apiObjects;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Vladimir.
 */
public class Results extends ApiObject {

    public Results(APIClient client) {
        super(client);
    }

    public JSONArray getResults(Long testId) throws APIException, IOException {

        String uri = String.format("get_results/%s",testId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONArray getResultsForCase(Long runId, Long caseId) throws APIException, IOException {

        String uri = String.format("get_results_for_case/%s/%s",runId, caseId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONArray getResultsForRun(Long runId) throws APIException, IOException {

        String uri = String.format("get_results_for_run/%s", runId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONObject addResult(Map data, Long testId) throws APIException, IOException {

        String uri = String.format("add_result/%s",testId);
        return (JSONObject) client.sendPost(uri, data);
    }


    public JSONObject addResultForCase(Map data, Long runId, Long caseId) throws APIException, IOException {

        String uri = String.format("add_result_for_case/%s/%s", runId, caseId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public JSONArray addResults(Map results, Long runId) throws IOException, APIException {

        String uri = String.format("add_results/%s", runId);
        return (JSONArray) client.sendPost(uri, results);
    }

    public JSONArray addResultsForCases(Map data, Long runId) throws APIException, IOException {

        String uri = String.format("add_results_for_cases/%s", runId);
        return (JSONArray) client.sendPost(uri, data);
    }
}
