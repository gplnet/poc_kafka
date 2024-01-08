package com.pichincha.poc.system.post.service.domain.exception;

import com.pichincha.poc.system.domain.exception.DomainException;

public class PostNotFoundException extends DomainException {
    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
