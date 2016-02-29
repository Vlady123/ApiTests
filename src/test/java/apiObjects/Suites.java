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
public class Suites extends ApiObject {
    public Suites(APIClient client) {
        super(client);
    }

    public JSONArray getSuites(Long projectId) throws APIException, IOException {

        String uri = String.format("get_suites/%s", projectId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONObject getSuite(Long suiteId) throws APIException, IOException {

        String uri = String.format("get_suite/%s", suiteId);
        return (JSONObject) client.sendGet(uri);
    }

    public JSONObject addSuite(Map suiteMap, Long projectId) throws APIException, IOException {

        String uri = String.format("add_suite/%s", projectId);
        return (JSONObject) client.sendPost(uri,suiteMap);
    }

    public JSONObject updateSuite(Map suiteMap, Long suiteId) throws APIException, IOException {

        String uri = String.format("update_suite/%s", suiteId);
        return (JSONObject) client.sendPost(uri, suiteMap);
    }

    public JSONObject deleteSuite(Long suiteId) throws APIException, IOException {

        String uri = String.format("delete_suite/%s", suiteId);
        HashMap map = new HashMap();
        return (JSONObject) client.sendPost(uri, map);
    }


}
