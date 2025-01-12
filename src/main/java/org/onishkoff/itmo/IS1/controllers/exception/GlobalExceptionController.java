package org.onishkoff.itmo.IS1.controllers.exception;


import io.jsonwebtoken.ExpiredJwtException;
import org.hibernate.exception.ConstraintViolationException;
import org.onishkoff.itmo.IS1.exception.BaseException;
import org.onishkoff.itmo.IS1.exception.DragonNotFoundException;
import org.onishkoff.itmo.IS1.exception.WrongPasswordException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(WrongPasswordException.class)
    public ProblemDetail wrongPasswordExceptionManager(WrongPasswordException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.getHttpStatus(), exception.getMessage());
        problemDetail.setProperty("description", "Введен неправильный пароль");
        return problemDetail;
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail expiredJwtExceptionManager(ExpiredJwtException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, exception.getMessage());
        problemDetail.setProperty("description", "Token Expired");
        return problemDetail;
    }


    @ExceptionHandler(DragonNotFoundException.class)
    public ProblemDetail dragonNotFoundExceptionManager(DragonNotFoundException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.getHttpStatus(), exception.getMessage());
        problemDetail.setProperty("description", "Dragon Not Found");
        return problemDetail;

    }


    @ExceptionHandler(BaseException.class)
    public ProblemDetail baseExceptionManager(BaseException exception){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.getHttpStatus(), exception.getMessage());
        problemDetail.setProperty("description", exception.getMessage());
        return problemDetail;
    }


    @ExceptionHandler(NullPointerException.class)
    public ProblemDetail nullPointerExceptionManager(){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Переданы невалидные значения");
        problemDetail.setProperty("description", "Переданы невалидные значения");
        return problemDetail;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException constraintEx) {
            if (constraintEx.getSQLException().getMessage().contains("users_login_key")) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Пользователь с таким логином уже существует.");
            }
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Нарушение целостности данных: " + ex.getMessage());
    }



}
