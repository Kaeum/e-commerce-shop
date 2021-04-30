package com.maeng.shop.sales.exception;

import com.maeng.shop.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CannotCancelException.class)
    public ResponseEntity<CommonResponse<Void>> handleCityNotFoundException(
            CannotCancelException ex) {

        CommonResponse<Void> response = CommonResponse.Builder.builder()
            .returnCode(CommonResponse.COMMON_RETURN_CODE_5000)
            .returnMessage(ex.getMessage())
            .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
