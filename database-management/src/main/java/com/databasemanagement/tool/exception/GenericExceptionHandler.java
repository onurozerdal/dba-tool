package com.databasemanagement.tool.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The type Generic exception handler.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GenericExceptionHandler.class);
    /**
     * Handle challenge exception handler response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleChallengeExceptionHandler(BaseException ex) {
        logger.error("Error: ", ex);
        return new ResponseEntity<Object>(ex, ex.getStatusCode());
    }

    /**
     * Handle challenge exception handler response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleChallengeExceptionHandler(Exception ex) {
        logger.error("Error: ", ex);
        BaseException baseException = new BaseException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Object>(baseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
