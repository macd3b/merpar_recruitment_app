package com.merpar.contentanalyzer.controller;

import com.merpar.contentanalyzer.controller.model.response.ErrorResponse;
import com.merpar.contentanalyzer.exception.ContentException;
import com.thoughtworks.xstream.XStreamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice(assignableTypes = {ContentController.class})
public class ContentControllerAdvice {

    @ExceptionHandler(value = {XStreamException.class})
    public ResponseEntity<ErrorResponse> handleXStreamException() {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ErrorResponse("Resource not found"));
    }

    @ExceptionHandler(value = {ContentException.class})
    public ResponseEntity<ErrorResponse> handleContentException() {
        return ResponseEntity
                .status(UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse("Resource fetching error"));
    }
}
