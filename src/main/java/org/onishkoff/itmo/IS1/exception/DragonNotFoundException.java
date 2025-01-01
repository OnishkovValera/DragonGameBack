package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class DragonNotFoundException extends BaseException {

    public DragonNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
