import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Main {
    private static final String NASA_API_KEY = "https://api.nasa.gov/planetary/apod?api_key=nAYVccpXVlg4FnRwjoNvz1kqC0FbSBkdc77i7sKC";

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().
                setUserAgent("MyTestService").
                setDefaultRequestConfig(RequestConfig.custom().
                        setConnectTimeout(5000).
                        setSocketTimeout(30000).
                        setRedirectsEnabled(false).
                        build()).
                build();


        HttpGet request = new HttpGet(NASA_API_KEY);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = client.execute(request);

        NasaInformation article = objectMapper.readValue(response.getEntity().getContent(), NasaInformation.class);

        article.saveInformation();

    }
}
