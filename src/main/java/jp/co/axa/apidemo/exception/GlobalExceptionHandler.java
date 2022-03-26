package jp.co.axa.apidemo.exception;

import jp.co.axa.apidemo.constants.ApiCodes;
import jp.co.axa.apidemo.helper.ResponseProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
        return ResponseProvider.fail(ex.getHttpStatus(), ex.getErrorCode(), ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return ResponseProvider.fail(HttpStatus.BAD_REQUEST, ApiCodes.MISSING_DATA, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseProvider.fail(HttpStatus.BAD_REQUEST, ApiCodes.INVALID_DATA, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return ResponseProvider.fail(
                status,
                ex.getBindingResult().getFieldError().getCode(),
                ex.getBindingResult().getFieldError().getDefaultMessage()
        );
    }
}
