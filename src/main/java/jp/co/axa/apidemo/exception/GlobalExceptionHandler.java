package jp.co.axa.apidemo.exception;

import jp.co.axa.apidemo.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomException(MyCustomException ex) {

        ApiResponse response = new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(ex.getHttpStatus());
        response.setCode(ex.getErrorCode());

        return new ResponseEntity<ApiResponse<?>>(response, ex.getHttpStatus());
    }
}
