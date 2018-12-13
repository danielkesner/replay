package model.har;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.http.HttpExchangeMetadata;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HarFile {

    private Set<HttpExchangeMetadata> httpExchanges;

    @SuppressWarnings("unchecked")
    @JsonProperty("log")
    private void extractHttpExchangeMetadata(Map<String, Object> log) {
        // List<Map<String,Object>> where each value is usually another nested Map<String,Object>
        List<Map<String, Object>> entries = (List<Map<String, Object>>) log.get("entries");

        this.httpExchanges = entries.stream().map(requestResponsePair -> {
            Map<String, Object> request = (Map<String, Object>) requestResponsePair.get("request");
            Map<String, Object> response = (Map<String, Object>) requestResponsePair.get("response");
            String requestMethod = (String) request.get("method");
            String requestUrl = (String) request.get("url");
            String requestBody = null;
            if (request.get("postData") != null) {
                requestBody = (String) ((Map<String, Object>) request.get("postData")).get("text");
            }
            String responseBody = (String) ((Map<String, Object>) response.get("content")).get("text");
            int responseStatus = (int) response.get("status");
            return new HttpExchangeMetadata(requestMethod, requestUrl, requestBody, responseBody, responseStatus);
        }).collect(Collectors.toSet());
    }
}
