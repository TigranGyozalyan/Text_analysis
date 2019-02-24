package am.aca.twitter.text.analysis.configurations;

import am.aca.twitter.text.analysis.entities.documents.Documents;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class TextAnalysisConfiguration {


    private final String ACCESSKEY = "033fe2d6ad344fa58540c6953c065402";

    private final String HOST = "https://eastasia.api.cognitive.microsoft.com";

    private final String PATH = "/text/analytics/v2.0/sentiment";


    public HttpsURLConnection getConnection() {
        try {
            URL url = new URL(HOST + PATH);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/json");
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", ACCESSKEY);
            connection.setDoOutput(true);

            return connection;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
