package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class DragonHeadNotFoundException extends BaseException {

    public DragonHeadNotFoundException() {
        super("Нет такой головы", HttpStatus.NOT_FOUND);
    }
}
