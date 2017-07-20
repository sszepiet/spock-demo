package pl.sii.eu.spockdemo.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    private ErrorCode errorCode;
    private String message;

    public enum ErrorCode {
        NOT_FOUND,
        INSUFFICIENT_STOCK,
        INTERNAL_SERVER_ERROR
    }
}
