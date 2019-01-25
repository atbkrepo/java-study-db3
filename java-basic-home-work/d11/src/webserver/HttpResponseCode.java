package webserver;

public enum HttpResponseCode {
    NOT_FOUND(404,"Not Found"), OK(200,"OK"), INTERNAL_SERVER_ERROR(500,"Internal Server Error");
    private int value;
    private String desc;

    HttpResponseCode(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int GetCode(HttpResponseCode code){
        return code.value;
    }

    public String GetDesc(HttpResponseCode code){
        return code.desc;
    }

    @Override
    public String toString() {
        return this.value+" "+this.desc;
    }
}
