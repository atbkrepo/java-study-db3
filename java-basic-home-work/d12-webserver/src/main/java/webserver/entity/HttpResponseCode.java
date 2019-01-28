package webserver.entity;

public enum HttpResponseCode {
    OK (200, "OK"),
    NOT_FOUND(404, "Not found"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server error"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed");

    private int value;
    private String desc;

    HttpResponseCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return this.value+" "+this.desc;
    }
}
