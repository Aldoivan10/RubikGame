package com.aldoivan.rubickgame.exception;

public class RubikException extends Exception
{
    public RubikException() { }

    public RubikException(String message) { super(message); }

    public RubikException(String message, Throwable cause) { super(message, cause); }

    public RubikException(Throwable cause) { super(cause); }

    public RubikException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { super(message, cause, enableSuppression, writableStackTrace); }
}
