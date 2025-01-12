package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class LocationNotFoundException extends BaseException {

    public LocationNotFoundException() {
        super("Не существует данной локации", HttpStatus.NOT_FOUND);
    }
}
