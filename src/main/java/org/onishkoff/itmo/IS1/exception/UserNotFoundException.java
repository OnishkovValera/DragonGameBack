package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;


public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("Не существует такого пользователя", HttpStatus.NOT_FOUND);
    }
}
