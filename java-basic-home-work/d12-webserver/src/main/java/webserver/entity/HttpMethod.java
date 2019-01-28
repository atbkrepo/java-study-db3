package webserver.entity;
public enum HttpMethod {
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    PATH("PATH"),
    POST("POST"),
    PUT("PUT");

    private String name;

    HttpMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
