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
    private String responsePayload;
    private int status;

    public HttpExchangeMetadata(String requestMethod, String requestUrl, String responsePayload, int status) {
        this(requestMethod, requestUrl, null, responsePayload, status);
    }
}
