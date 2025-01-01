package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends BaseException {
    public WrongPasswordException() {
        super("Неправильный пароль", HttpStatus.UNAUTHORIZED);
    }
}
