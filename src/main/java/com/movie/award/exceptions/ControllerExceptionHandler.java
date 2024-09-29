package com.movie.award.exceptions;

import com.movie.award.DTO.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity threatUserNotFoundException(UserNotFoundException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO( exception.getMessage(), "500");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(InvalidDataAccessException.class)
    public ResponseEntity threatInvalidDataAccessException(InvalidDataAccessException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),"406");
        return ResponseEntity.badRequest().body((exceptionDTO));
    }

    @ExceptionHandler(MovieAlreadyCreatedException.class)
    public ResponseEntity threatMovieAlreadyCreatedException(MovieAlreadyCreatedException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),"400");
        return ResponseEntity.badRequest().body((exceptionDTO));
    }
}
