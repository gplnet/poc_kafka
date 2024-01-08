package com.pichincha.poc.system.post.service.application.exception.handler;

import com.pichincha.poc.system.application.handler.ErrorDTO;
import com.pichincha.poc.system.application.handler.GlobalExceptionHandler;
import com.pichincha.poc.system.post.service.domain.exception.PostDomainException;
import com.pichincha.poc.system.post.service.domain.exception.PostNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class PostGlobalExceptionHandler extends GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {PostDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handlerexception(PostDomainException postDomainException){
        log.error(postDomainException.getMessage(), postDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(postDomainException.getMessage())
                .build();

    }

    @ResponseBody
    @ExceptionHandler(value = {PostNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handlerexception(PostNotFoundException postNotFountException){
        log.error(postNotFountException.getMessage(), postNotFountException);
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(postNotFountException.getMessage())
                .build();

    }
}
