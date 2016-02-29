package apiObjects;

import com.gurock.testrail.APIClient;


public abstract class ApiObject {

    public static APIClient client;


    public ApiObject(APIClient client) {
        setClient(client);
    }

    public void setClient(APIClient client) {
        this.client = client;
    }
}
