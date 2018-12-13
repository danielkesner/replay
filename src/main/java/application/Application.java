package application;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.HttpConstants;
import model.har.HarFile;
import model.http.HttpExchangeMetadata;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String... args) {
        File file = new File("src/main/resources/har/qa1.har");
        HarFile harFile;
        try {
            harFile = mapper.readValue(file, HarFile.class);
        } catch (IOException ioe) {
            throw new RuntimeException("Could not parse HarFile from file");
        }

        Set<HttpExchangeMetadata> data = harFile.getHttpExchanges();
        Set<HttpExchangeMetadata> validPaoCalls = data.stream()
                .filter(exchange -> HttpConstants.VALID_HTTP_METHODS.contains(exchange.getRequestMethod()))
                .filter(exchange -> exchange.getRequestUrl().contains("https://pao"))
                .collect(Collectors.toSet());

    }
}
