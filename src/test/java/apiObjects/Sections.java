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
public class Sections extends ApiObject {
    public Sections(APIClient client) {
        super(client);
    }

    public JSONObject addSection(Map data, Long projectId) throws APIException, IOException {


        String uri = String.format("add_section/%s", projectId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public JSONObject getSection(Long sectionId) throws IOException, APIException {

        String uri = String.format("get_section/%s", sectionId);
        return (JSONObject) client.sendGet(uri);
    }
    public JSONArray getSections(Long projectId, Long suiteId) throws IOException, APIException {

        String uri = String.format("get_sections/%s&suite_id=%s", projectId, suiteId);
        return (JSONArray) client.sendGet(uri);
    }

    public JSONObject updateSection(Map data, Long sectionId) throws APIException, IOException {


        String uri = String.format("update_section/%s", sectionId);
        return (JSONObject) client.sendPost(uri, data);
    }


    public JSONObject deleteSection(Long sectionId) throws APIException, IOException {

        HashMap map = new HashMap();
        String uri = String.format("delete_section/%s", sectionId);
        return (JSONObject) client.sendPost(uri, map);
    }
}
