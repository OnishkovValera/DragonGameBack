package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class DragonCaveNotFoundException extends BaseException {
    public DragonCaveNotFoundException() {
        super("Не существует такой пещеры", HttpStatus.NOT_FOUND);
    }
}

