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
public class Projects extends ApiObject {
    public Projects(APIClient client) {
        super(client);
    }

    public JSONObject getProject(Long projectId) throws APIException, IOException {

        String uri = String.format("get_project/%s", projectId);
        return (JSONObject) client.sendGet(uri);
    }

    public JSONArray getProjects() throws APIException, IOException {

        String uri = "get_projects";
        return (JSONArray) client.sendGet(uri);
    }

    public JSONObject addProject(Map data) throws APIException, IOException {

        String uri = "add_project";
        return (JSONObject) client.sendPost(uri, data);
    }

    public JSONObject updateProject(Map data, Long projectId) throws APIException, IOException {

        String uri = String.format("update_project/%s", projectId);
        return (JSONObject) client.sendPost(uri, data);
    }

    public Object deleteProject(Long projectId) throws APIException, IOException {
        HashMap data = new HashMap();
        String uri = String.format("delete_project/%s", projectId);
        return client.sendPost(uri, data);
    }


}
