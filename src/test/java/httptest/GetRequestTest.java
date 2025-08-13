package httptest;

import http.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Classe responsável por executar testes para requisições do tipo GET.
 * Pode ser usada para validar endpoints HTTP usando parâmetros customizados.
 *
 * @author Douglas Williams Ferreira da Silva
 * @version 1.0
 */

public class GetRequestTest {

    private final Logger logger = Logger.getLogger(GetRequestTest.class.getName());
    private HttpResponse<String> response;
    private final int timeout = 10000;
    private final Map<String, String> headers = new HashMap<>();
    private final String method = "GET";
    private final HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.noBody();
    private final HttpClient.Version version = HttpClient.Version.HTTP_1_1;

    @Test
    void testGetStatusCodeSuccess() {
        logger.info("test -> Status code 200 (Success)");
        response = Request.execute("https://http.cat/", timeout, headers,
                method, bodyPublisher, version);
        assert response != null;
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void testGetStatusCodeBlocked() {
        logger.info("test -> Status code 403 (Forbidden Selector)");
        response = Request.execute("https://super.walmart.com.mx/", timeout, headers,
                method, bodyPublisher, version);
        Assertions.assertEquals(403, response.statusCode());
    }

    @Test
    void testGetStatusCodeBadRequest() {
        logger.info("test -> Status code 400 (Bad Request)");
        response = Request.execute("https://api.vtex.com/", timeout, headers,
                method, bodyPublisher, version);
        Assertions.assertEquals(400, response.statusCode());
    }
}
