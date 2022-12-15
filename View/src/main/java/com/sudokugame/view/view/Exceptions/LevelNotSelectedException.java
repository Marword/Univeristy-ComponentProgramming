package com.sudokugame.view.view.Exceptions;

public class LevelNotSelectedException extends NullPointerException{
    public LevelNotSelectedException(Throwable cause) {
        super(String.valueOf(cause));
    }
}
