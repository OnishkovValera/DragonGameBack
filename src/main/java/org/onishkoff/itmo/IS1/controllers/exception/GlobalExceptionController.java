package org.onishkoff.itmo.IS1.controllers.exception;


import io.jsonwebtoken.ExpiredJwtException;
import org.onishkoff.itmo.IS1.exception.DragonNotFoundException;
import org.onishkoff.itmo.IS1.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(WrongPasswordException.class)
    public ProblemDetail wrongPasswordExcpetionManager(WrongPasswordException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.getHttpStatus(), exception.getMessage());
        problemDetail.setProperty("description", "Введен неправильный пароль");
        return problemDetail;
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail expiredJwtExcpetionManager(ExpiredJwtException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
        problemDetail.setProperty("description", "Token Expired");
        return problemDetail;
    }


    @ExceptionHandler(DragonNotFoundException.class)
    public ProblemDetail DragonNotFoundExcpetionManager(DragonNotFoundException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setProperty("description", "Dragon Not Found");
        return problemDetail;

    }



}
