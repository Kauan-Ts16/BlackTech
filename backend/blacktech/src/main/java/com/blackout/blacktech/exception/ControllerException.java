package com.blackout.blacktech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(ExceptionGeneric.class)
    public ResponseEntity<ResponseBody> returnException(ExceptionGeneric exception) {
        return ResponseEntity.status(exception.getResponseBody().getStatus()).body(exception.getResponseBody());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBody> returnException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseBody(
                    exception.getClass().getSimpleName(),
                    "INFORMAÇÕES INCOMPÁTIVEIS COM O ESPERADO",
                    HttpStatus.BAD_REQUEST.value()
            )
        );
    }
}
