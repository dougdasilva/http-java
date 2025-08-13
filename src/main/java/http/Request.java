package http;

import credentials.EnvironmentVariables;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.logging.Logger;

import static util.RequestUtil.getHeaders;

public class Request {
    private static final Logger LOGGER = Logger.getLogger(Request.class.getName());
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofMillis(EnvironmentVariables.connectTimeout))
            .build();

    public static HttpResponse<String> execute(String url, int timeout,
                                               Map<String, String> headers,
                                               String method,
                                               HttpRequest.BodyPublisher bodyPublisher,
                                               HttpClient.Version version) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .timeout(Duration.ofMillis(timeout))
                    .version(version)
                    .method(method, bodyPublisher);

            getHeaders(headers, requestBuilder);

            HttpRequest httpRequest = requestBuilder.build();
            return CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            LOGGER.severe(() -> String.format("Erro ao executar %s %s: %s", method, url, e.getMessage()));
            throw new RuntimeException("Erro ao executar requisição HTTP", e);
        }
    }

}
