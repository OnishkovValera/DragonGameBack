package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class AdminRequestNotFoundException extends BaseException {
    public AdminRequestNotFoundException() {
        super("Не существует такой заявки", HttpStatus.NOT_FOUND);
    }
}
