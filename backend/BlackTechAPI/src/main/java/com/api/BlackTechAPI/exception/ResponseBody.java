package com.api.BlackTechAPI.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseBody {

    private final String title, message;
    private final int status;
    private final LocalDateTime time = LocalDateTime.now();

    public ResponseBody(String title, String message, int status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }
}
