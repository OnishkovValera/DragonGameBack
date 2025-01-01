package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends BaseException {
    public PersonNotFoundException() {
        super("Нет такого персонажа", HttpStatus.NOT_FOUND);
    }
}
