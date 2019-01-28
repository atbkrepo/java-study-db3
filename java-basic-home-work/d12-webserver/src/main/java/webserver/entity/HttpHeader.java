package webserver.entity;

import java.net.URI;
import java.util.Map;

public class HttpHeader {
    private HttpMethod httpMethod;
    private String uri;
    private String httpProtocol;
    private Map<String,String> headers;

    public HttpHeader(HttpMethod httpMethod, String uri, String httpProtocol, Map<String,String> headers) {
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpProtocol() {
        return httpProtocol;
    }

    public void setHttpProtocol(String httpProtocol) {
        this.httpProtocol = httpProtocol;
    }

    public Map<String,String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String,String> headers) {
        this.headers = headers;
    }
}
