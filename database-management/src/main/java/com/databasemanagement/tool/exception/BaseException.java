package com.databasemanagement.tool.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

/**
 * The type Base exception.
 */
public class BaseException extends RuntimeException {

    private HttpStatus statusCode;
    private Date timestamp;
    private String detail;
    private List<String> params;

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     */
    public BaseException(String message) {
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.timestamp = new Date();
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message    the message
     * @param statusCode the status code
     */
    public BaseException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.timestamp = new Date();
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message    the message
     * @param statusCode the status code
     * @param detail     the detail
     */
    public BaseException(String message, HttpStatus statusCode, String detail) {
        super(message);
        this.statusCode = statusCode;
        this.detail = detail;
        this.timestamp = new Date();
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message    the message
     * @param statusCode the status code
     * @param detail     the detail
     * @param params     the params
     */
    public BaseException(String message, HttpStatus statusCode, String detail, List<String> params) {
        super(message);
        this.statusCode = statusCode;
        this.detail = detail;
        this.timestamp = new Date();
        this.params = params;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     */
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets detail.
     *
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets detail.
     *
     * @param detail the detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Gets params.
     *
     * @return the params
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * Sets params.
     *
     * @param params the params
     */
    public void setParams(List<String> params) {
        this.params = params;
    }
}
