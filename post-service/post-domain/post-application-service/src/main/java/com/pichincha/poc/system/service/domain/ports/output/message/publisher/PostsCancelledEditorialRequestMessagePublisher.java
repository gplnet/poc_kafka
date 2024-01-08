package com.pichincha.poc.system.service.domain.ports.output.message.publisher;

import com.pichincha.poc.system.domain.event.publisher.DomainEventPublisher;
import com.pichincha.poc.system.post.service.domain.event.PostsCancelledEvent;

public interface PostsCancelledEditorialRequestMessagePublisher extends DomainEventPublisher<PostsCancelledEvent> {
}
