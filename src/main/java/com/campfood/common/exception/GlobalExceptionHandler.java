package com.campfood.common.exception;

import com.campfood.common.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistMemberException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistMemberException(AlreadyExistMemberException ex){
        log.error("handleAlreadyExistMemberException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(CreateAuthCodeException.class)
    public ResponseEntity<ErrorResponse> handleCreateAuthCodeException(CreateAuthCodeException ex){
        log.error("handleCreateAuthCodeException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedEmailException(DuplicatedEmailException ex){
        log.error("handleDuplicatedEmailException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(DuplicatedLoginIdException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedLoginIdException(DuplicatedLoginIdException ex){
        log.error("handleDuplicatedLoginIdException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(InputNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInputNotFoundException(InputNotFoundException ex){
        log.error("handleInputNotFoundException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(MemberNotExistException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotExistException(MemberNotExistException ex){
        log.error("handleMemberNotExistException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException ex){
        log.error("handlePasswordMismatchException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenExpiredException(RefreshTokenExpiredException ex){
        log.error("handleRefreshTokenExpiredException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ErrorResponse> handleRestApiException(RestApiException ex){
        log.error("handleRestApiException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(TokenNullException.class)
    public ResponseEntity<ErrorResponse> handleTokenNullException(TokenNullException ex){
        log.error("handleTokenNullException",ex);
        final ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }



}
