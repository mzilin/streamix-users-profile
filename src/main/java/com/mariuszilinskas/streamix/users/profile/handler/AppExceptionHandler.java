package com.mariuszilinskas.streamix.users.profile.handler;

import com.mariuszilinskas.streamix.users.profile.exception.*;
import com.mariuszilinskas.streamix.web.handler.CommonExceptionHandler;
import com.mariuszilinskas.streamix.web.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a global exception handler that handles exceptions thrown across the user profile service.
 *
 * @author Marius Zilinskas
 */
@RestControllerAdvice
public class AppExceptionHandler extends CommonExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IncorrectFileException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectFileException(IncorrectFileException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
