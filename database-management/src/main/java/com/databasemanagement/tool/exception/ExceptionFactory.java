package com.databasemanagement.tool.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * The type Exception factory.
 */
public class ExceptionFactory {

    private ExceptionFactory() {
    }

    /**
     * Throw exception.
     *
     * @param message the message
     */
    public static void throwException(String message) {
        throw new BaseException(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Throw exception.
     *
     * @param message    the message
     * @param httpStatus the http status
     */
    public static void throwException(String message, HttpStatus httpStatus) {
        throw new BaseException(message, httpStatus);
    }

    /**
     * Throw exception.
     *
     * @param message    the message
     * @param httpStatus the http status
     * @param params     the params
     */
    public static void throwException(String message, HttpStatus httpStatus, String... params) {
        throw new BaseException(message, httpStatus, null, Arrays.asList(params));
    }
}
