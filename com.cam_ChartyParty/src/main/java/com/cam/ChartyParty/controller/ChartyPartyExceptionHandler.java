package com.cam.ChartyParty.controller;

import com.cam.ChartyParty.dao.DataNotFoundException;
import com.cam.ChartyParty.service.BadUserInputException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * need to build the pom for this to work
 *
 * @author chelseamiller
 */
@ControllerAdvice
@RestController
public class ChartyPartyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<Error> handleSqlException(
            EmptyResultDataAccessException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage("No data found");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Error> handlePersistenceException(
            DataNotFoundException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MysqlDataTruncation.class)
    public final ResponseEntity<Error> handleMysqlDataTruncationException(
            MysqlDataTruncation ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
       @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public final ResponseEntity<Error> handleStringIndexOutOfBoundsException(
           StringIndexOutOfBoundsException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    
         @ExceptionHandler(BadUserInputException.class)
    public final ResponseEntity<Error> handleBadUserInputException(
           BadUserInputException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    
    
/*
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Error> handleHttpMessageNotReadableExceptionException(
            HttpMessageNotReadableException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
*/
}
