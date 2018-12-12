package model.global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequestResponsePair {

    private String requestMethod;
    private String requestUrl;
    private String responsePayload;
    private int status;
}
