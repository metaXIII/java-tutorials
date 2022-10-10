package com.metaxiii.fr.errorhandlingforrestwithspring.exceptions;

public class CustomException extends RuntimeException {
    public CustomException(final String message) {
        super(message);
    }
}
