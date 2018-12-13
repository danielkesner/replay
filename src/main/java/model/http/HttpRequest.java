package model.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpRequest {
    @JsonProperty("method")
    private String requestMethod;
    @JsonProperty("url")
    private String requestUrl;
    private String requestBody;

    @JsonProperty("postData")
    private void getRequestBody(Map<String, String> postData) {
        if (postData.get("text") != null) {
            this.requestBody = postData.get("text");
        }
    }

}
