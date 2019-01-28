package webserver.exceptions;

import webserver.entity.HttpResponseCode;

public class ServerException extends RuntimeException {
    private HttpResponseCode errorType;

    public ServerException(HttpResponseCode errorType) {
        this.errorType = errorType;
    }

    public HttpResponseCode getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorType.getDesc();
    }
}