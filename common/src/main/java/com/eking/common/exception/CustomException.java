package com.eking.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zeke929
 */
@Setter
@Getter
public class CustomException extends RuntimeException {
    private final String code;
    private final String message;

    public CustomException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
        this.code = "-1";
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = "-1";
    }


    @Override
    public String getMessage() {
        return this.message;
    }
}
