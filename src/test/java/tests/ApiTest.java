package tests;

import com.gurock.testrail.APIClient;
import org.junit.BeforeClass;

/**
 * Created by Vladimir.
 */
public abstract class ApiTest {

    protected static APIClient client;
    protected final static String HOSTNAME = "https://qiwistuff.testrail.net/";
    protected final static String EMAIL = "vladimir.kerimoff73@yandex.ru";
    protected final static String PASSWORD = "SuAN1BfIwd8UPh21la3p";


    @BeforeClass
    public static void setup() {
        client = new APIClient(HOSTNAME);
        client.setUser(EMAIL);
        client.setPassword(PASSWORD);
    }



}