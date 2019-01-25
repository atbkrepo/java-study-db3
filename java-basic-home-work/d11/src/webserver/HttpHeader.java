package webserver;

import java.net.URI;
import java.util.List;

public class HttpHeader {
    private HttpMethod httpMethod;
    private URI uri;
    private String httpProtocol;
    private List<String> headers;

    public HttpHeader(HttpMethod httpMethod, URI uri, String httpProtocol, List<String> headers) {
        this.httpMethod = httpMethod;
        this.uri = uri;
        this.httpProtocol = httpProtocol;
        this.headers = headers;
    }

    public HttpHeader() {
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getHttpProtocol() {
        return httpProtocol;
    }

    public void setHttpProtocol(String httpProtocol) {
        this.httpProtocol = httpProtocol;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
