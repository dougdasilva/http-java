package httptest;

import http.ExecuteRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ExecuteRequestTest {

    private HttpResponse<String> response;
    private int timeout = 10000;
    private int statusCode;
    private Map<String, String> headers = new HashMap<>();

    @Test
    void testGetStatusCodeSuccess() {
        response = ExecuteRequest.getRequest("https://http.cat/", timeout, headers);
        statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    void testGetStatusCodeBlocked() {
        response = ExecuteRequest.getRequest("https://super.walmart.com.mx/", timeout, headers);
        statusCode = response.statusCode();
        Assertions.assertEquals(403, statusCode);
    }
}
