package hu.feladat.elastic.controller;

import hu.feladat.elastic.exception.ElasticException;
import hu.feladat.elastic.dto.ErrorDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ElasticException.class)
    public ErrorDto handleElasticException(ElasticException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrors(e.getMessages());
        return errorDto;
    }
}
