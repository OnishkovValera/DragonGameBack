package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class AdminRequestAlreadyExists extends BaseException {
    public AdminRequestAlreadyExists() {
        super("Заявка уже подана", HttpStatus.CONFLICT);
    }
}
