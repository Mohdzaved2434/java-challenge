package jp.co.axa.apidemo.exception;

import jp.co.axa.apidemo.constants.ApiCodes;
import jp.co.axa.apidemo.model.response.AxaApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<Object> handleCustomException(MyCustomException ex) {
        AxaApiResponse response = new AxaApiResponse();
        response.setStatus(ex.getHttpStatus());
        response.setCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        AxaApiResponse response = new AxaApiResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setCode(ApiCodes.INVALID_DATA);
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        AxaApiResponse response = new AxaApiResponse();
        response.setStatus(status);
        response.setCode(ex.getBindingResult().getFieldError().getCode());
        response.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
