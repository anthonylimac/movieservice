package com.movie.award.exceptions;


public class InvalidDataAccessException extends IllegalArgumentException {
    public InvalidDataAccessException(String message){
        super(message);
    }
}
