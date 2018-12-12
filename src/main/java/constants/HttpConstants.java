package constants;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class HttpConstants {

    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";

    public static final Set<String> VALID_HTTP_METHODS = ImmutableSet.of(HTTP_GET, HTTP_POST, HTTP_PUT);
}
