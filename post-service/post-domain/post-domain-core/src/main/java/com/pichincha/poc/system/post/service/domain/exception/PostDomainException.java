package com.pichincha.poc.system.post.service.domain.exception;

import com.pichincha.poc.system.domain.exception.DomainException;

public class PostDomainException extends DomainException {
    public PostDomainException(String message) {
        super(message);
    }

    public PostDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
