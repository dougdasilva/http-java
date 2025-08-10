package util;

import java.net.http.HttpRequest;
import java.util.Map;

public class RequestUtil {

    public static void getHeaders(Map<String, String> headers, HttpRequest.Builder requestBuilder) {
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::header);
        }
    }
}
