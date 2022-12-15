package com.sudokugame.view.view.Exceptions;

public class MissingLevelException extends NullPointerException{
    public MissingLevelException(Throwable cause) {
        super(String.valueOf(cause));
    }
}
