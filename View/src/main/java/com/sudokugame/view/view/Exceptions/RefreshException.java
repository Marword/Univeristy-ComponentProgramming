package com.sudokugame.view.view.Exceptions;


import java.io.IOException;

public class RefreshException extends IOException {
    public RefreshException(Throwable cause) {
        super(String.valueOf(cause));
    }
}
