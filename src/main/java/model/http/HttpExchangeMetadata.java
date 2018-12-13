package model.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpExchangeMetadata {
    private String requestMethod;
    private String requestUrl;
    private String requestBody;
    private String responseBody;
    private int status;

    public HttpExchangeMetadata(String requestMethod, String requestUrl, String responseBody, int status) {
        this(requestMethod, requestUrl, null, responseBody, status);
    }
}
