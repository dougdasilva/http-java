package httptest;

import http.ExecuteRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ExecuteRequestStatusCodeTest {

    private HttpResponse<String> response;
    private final int timeout = 10000;
    private final Map<String, String> headers = new HashMap<>();

    @Test
    void testGetStatusCodeSuccess() {
        response = ExecuteRequest.getRequest("https://http.cat/", timeout, headers);
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void testGetStatusCodeBlocked() {
        response = ExecuteRequest.getRequest("https://super.walmart.com.mx/", timeout, headers);
        Assertions.assertEquals(403, response.statusCode());
    }
}
