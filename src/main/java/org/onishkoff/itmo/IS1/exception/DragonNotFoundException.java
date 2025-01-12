package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class DragonNotFoundException extends BaseException {

    public DragonNotFoundException() {
        super("Не существует такого дракона", HttpStatus.NOT_FOUND);
    }
}
