package org.onishkoff.itmo.IS1.exception;

import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends BaseException {

    public TeamNotFoundException() {
        super("Такой команды не существует", HttpStatus.NOT_FOUND);
    }



}
