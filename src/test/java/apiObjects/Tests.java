package apiObjects;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by Vladimir.
 */
public class Tests extends ApiObject{

    public Tests(APIClient client) {
        super(client);
    }

    public JSONObject getTest(Long testId) throws IOException, APIException {

        String uri = String.format("get_test/%s", testId);
        return (JSONObject) client.sendGet(uri);
    }

    public JSONArray getTests(Long runId) throws IOException, APIException {

        String uri = String.format("get_tests/%s", runId);
        return (JSONArray) client.sendGet(uri);
    }
}
