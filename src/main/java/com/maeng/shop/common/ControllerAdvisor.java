package com.maeng.shop.common;

import com.maeng.shop.order.exception.CannotCancelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CannotCancelException.class)
    public ResponseEntity<CommonResponse<Void>> handleCannotCancelException(
            CannotCancelException exception) {
        return new ResponseEntity<>(CommonResponse.onFailure(exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
