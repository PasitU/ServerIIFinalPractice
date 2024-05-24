package com.example.finalrestprac.controllers;

import com.example.finalrestprac.exceptions.ErrorForm;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice(assignableTypes = {CustomerController.class, FileController.class})
public class GreatAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity validationError(MethodArgumentNotValidException e, WebRequest webRequest){
        ErrorForm errorForm = new ErrorForm(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Error: validation fields error", webRequest.getDescription(false));
        for (FieldError fe: e.getFieldErrors()) {
            errorForm.addValidationError(fe.getField(), fe.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorForm);
    }

    @ExceptionHandler({HandlerMethodValidationException.class})
    public ResponseEntity handlerValidationError(HandlerMethodValidationException he, WebRequest wq){
        ErrorForm errorForm = new ErrorForm(HttpStatus.I_AM_A_TEAPOT.value(), "Invalid parameter", wq.getDescription(false));
        for (ParameterValidationResult pr : he.getAllValidationResults()){
            errorForm.addValidationError(pr.getMethodParameter().getParameterName(), pr.getResolvableErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errorForm);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralExceptions(Exception e, WebRequest wq){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorForm(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), wq.getDescription(false)));
    }
}
