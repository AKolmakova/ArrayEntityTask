package com.kolmakova.task2.exception;

public class ParseXmlException extends Exception {

    public ParseXmlException() {
        super();
    }

    public ParseXmlException(String message) {
        super(message);
    }

    public ParseXmlException(String message, Exception exception) {
        super(message, exception);
    }

    public ParseXmlException(Exception exception) {
        super(exception);
    }
}
