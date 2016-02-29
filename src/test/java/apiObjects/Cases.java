package apiObjects;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Cases extends ApiObject {


    public Cases(APIClient client){
        super(client);

    }

    public JSONObject getCase(Long caseId) throws APIException, IOException {


        String uri = String.format("get_case/%s", caseId);
        return (JSONObject) client.sendGet(uri);
    }

    public JSONArray getCases(Long projectId, Long suiteId) throws APIException, IOException {

        String uri = String.format("get_cases/%s&suite_id=%s", projectId, suiteId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONObject addCase(Map data, Long sectionId) throws APIException, IOException {

        String uri = String.format("add_case/%s", sectionId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public JSONObject updateCase(Map data, Long caseId) throws APIException, IOException {


        String uri = String.format("update_case/%s", caseId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public void deleteCase(Long caseId) throws APIException, IOException {

        HashMap map = new HashMap();
        String uri = String.format("delete_case/%s", caseId);
        client.sendPost(uri, map);
    }

}
