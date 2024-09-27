package ApiControllers;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalTime;

public class TimeService {

    public LocalTime getCurrentTimeFromInternet() {
        String url = "http://worldtimeapi.org/api/timezone/Asia/Tokyo";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = new JSONObject(json);
                String dateTime = jsonObject.getString("datetime");
                return LocalTime.parse(dateTime.substring(11, 19));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

