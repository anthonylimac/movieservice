package com.movie.award.exceptions;


public class MovieAlreadyCreatedException extends IllegalArgumentException {

    public MovieAlreadyCreatedException(String message){
        super(message);
    }
}
