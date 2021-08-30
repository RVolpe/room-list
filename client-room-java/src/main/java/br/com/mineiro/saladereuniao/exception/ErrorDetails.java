package br.com.mineiro.saladereuniao.exception;

import lombok.Builder;

import java.util.Date;

@Builder
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
