import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Verify {
    private HttpRequestFacade httpRequestFacade;
    private String resource = "/face/v1.0/verify";

    public Verify(){
        httpRequestFacade = new HttpRequestFacade(resource);
    }

    public String build(String detectedFace1, String detectedFace2){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("faceId1", detectedFace1);
        jsonObject.put("faceId2", detectedFace2);
        try {
            HttpResponse response = httpRequestFacade.getHttpResponse(
                    HttpRequestFacade.HttpRequestMethod.POST,
                    httpRequestFacade.getUriBuilder(""),
                    jsonObject);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity).trim();
            }
            return null;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
