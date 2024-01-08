package com.pichincha.poc.system.domain.event.publisher;

import com.pichincha.poc.system.domain.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent>{

    void publish(T domainEvent);
}
