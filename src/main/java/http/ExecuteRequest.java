package http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class ExecuteRequest {
    private static final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofMillis(5000))
            .build();

    private static final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

    public static HttpResponse<String> getRequest(String url, int timeout, Map<String, String> headers) {
        try {
            requestBuilder.GET()
                    .uri(new URI(url))
                    .timeout(Duration.ofMillis(timeout))
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            getHeaders(headers);
            HttpRequest httpRequest = requestBuilder.build();

            return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static void getHeaders(Map<String , String> headers){
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::header);
        }
    }
}
