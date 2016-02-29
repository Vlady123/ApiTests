package apiObjects;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir.
 */
public class Runs extends ApiObject {

    public Runs(APIClient client) {
        super(client);
    }

    public JSONObject getRun(Long runId) throws APIException, IOException {

        String uri = String.format("get_run/%s", runId);
        return (JSONObject) client.sendGet(uri);
    }

    public JSONArray getRuns(Long projectId) throws APIException, IOException {

        String uri = String.format("get_runs/%s", projectId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONObject addRun(Map data, Long projectId) throws APIException, IOException {

        String uri = String.format("add_run/%s", projectId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public JSONObject updateRun(Map data, Long runId) throws APIException, IOException {

        String uri = String.format("update_run/%s", runId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public JSONObject closeRun(Long runId) throws APIException, IOException {

        HashMap map = new HashMap();
        String uri = String.format("close_run/%s", runId);
        return (JSONObject) client.sendPost(uri, map);
    }

    public void deleteRun(Long runId) throws APIException, IOException {

        HashMap map = new HashMap();
        String uri = String.format("delete_run/%s", runId);
        client.sendPost(uri, map);
    }


}
