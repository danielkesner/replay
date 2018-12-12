package application;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.HttpConstants;
import model.har.Entry;
import model.har.HarFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

        List<Entry> entries = (harFile != null && harFile.getLog() != null) ?
                harFile.getLog().getEntries().stream()
                        .filter(e -> HttpConstants.VALID_HTTP_METHODS.contains(e.getRequest().getMethod()))
                        .collect(Collectors.toList()) : null;
        List<Entry> postRequestsWithBody = entries.stream()
                .filter(e -> e.getRequest().getMethod().equals(HttpConstants.HTTP_POST))
                .filter(e -> e.getRequest().getPostData() != null
                        && !e.getRequest().getPostData().getRequestBody().equals("{}")
                        && !e.getRequest().getPostData().getRequestBody().equals("[]"))
                .collect(Collectors.toList());

    }
}
