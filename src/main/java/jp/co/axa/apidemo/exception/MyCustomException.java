package jp.co.axa.apidemo.exception;

import org.springframework.http.HttpStatus;

public class MyCustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final String errorCode;
    private final HttpStatus httpStatus;

    public MyCustomException(String message,String errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;

    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
