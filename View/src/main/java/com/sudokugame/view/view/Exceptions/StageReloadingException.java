package com.sudokugame.view.view.Exceptions;


import java.io.IOException;

public class StageReloadingException extends IOException {
    public StageReloadingException(Throwable cause) {
        super(String.valueOf(cause));
    }
}
