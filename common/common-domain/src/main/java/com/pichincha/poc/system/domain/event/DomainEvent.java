package com.pichincha.poc.system.domain.event;

public interface DomainEvent <T>{
    void fire();
}
