package com.blackout.blacktech.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionGeneric extends RuntimeException{

    private final String title, message;
    private final int status;

    public ResponseBody getResponseBody() {
        return new ResponseBody(title, message, status);
    }
}
