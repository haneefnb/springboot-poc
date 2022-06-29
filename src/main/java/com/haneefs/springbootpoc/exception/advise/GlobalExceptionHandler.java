package com.haneefs.springbootpoc.exception.advise;

import com.haneefs.springbootpoc.constants.ErrorCodes;
import com.haneefs.springbootpoc.exception.RecordNotFoundException;
import com.haneefs.springbootpoc.model.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity handleCustomerNotFoundException(RecordNotFoundException c, WebRequest request){
        ErrorInfo error = ErrorInfo.builder().erroCode(ErrorCodes.CNF).errorMessage(c.getLocalizedMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }
}
