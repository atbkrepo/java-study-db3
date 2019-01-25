package webserver;

import java.io.*;
import java.net.URI;
import java.util.List;
import java.util.StringJoiner;

public class HttpResponse extends HttpHeader {

    public HttpResponse(HttpMethod httpMethod, URI uri, String httpProtocol, List<String> headers) {
        super(httpMethod, uri, httpProtocol, headers);

    }

    public HttpResponse() {
    }

    public HttpResponse(HttpHeader request) {
        this(request.getHttpMethod(), request.getUri(), request.getHttpProtocol(), null);
    }


    public String prepare(InputStream stream) throws IOException {


        StringJoiner joiner = new StringJoiner("\n");

        HttpResponseCode code = HttpResponseCode.OK;
        if (stream == null) {
            code = HttpResponseCode.NOT_FOUND;
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    joiner.add(line);
                }
            } catch (IOException e) {
                code = HttpResponseCode.INTERNAL_SERVER_ERROR;
            }

        }

        StringJoiner res = new StringJoiner("\n");

        res.add(getHttpProtocol() +" "+ code);
        res.add("\n");
        res.add("\n");
        res.merge(joiner);
        return res.toString();
    }


}
