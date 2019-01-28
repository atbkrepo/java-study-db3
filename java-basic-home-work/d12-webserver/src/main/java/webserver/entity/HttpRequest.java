package webserver.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class HttpRequest extends HttpHeader {

    public HttpRequest(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //read request till empty string
        String line;
        ArrayList<String> lines = new ArrayList<>(10);
        try {
            while (!(line = reader.readLine()).isEmpty()) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] line1 = lines.get(0).split(" ");

        super.setHttpMethod(HttpMethod.valueOf(line1[0]));
        super.setUri(line1[1]);
        super.setHttpProtocol(line1[2]);
        lines.remove(0);

        Map<String, String> headers = new HashMap<>();
        for (String row : lines) {

            String[] headerEntry = row.split(":");
            headers.put(headerEntry[0], headerEntry[1]);
        }
        super.setHeaders(headers);
    }
}
