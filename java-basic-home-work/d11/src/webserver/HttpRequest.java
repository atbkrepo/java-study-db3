package webserver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class HttpRequest extends HttpHeader{

    public HttpRequest(String header) throws URISyntaxException {
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(header.split("\n")));

        String[] line1 = lines.get(0).split(" ");

        super.setHttpMethod(HttpMethod.valueOf(line1[0]));
        super.setUri(new URI(line1[1]));
        super.setHttpProtocol(line1[2]);
        lines.remove(0);
        super.setHeaders(lines);
    }
}
