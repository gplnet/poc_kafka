package com.pichincha.poc.system.post.service.domain.event;

import com.pichincha.poc.system.domain.event.publisher.DomainEventPublisher;
import com.pichincha.poc.system.post.service.domain.entity.Post;

import java.time.ZonedDateTime;

public class PostsCancelledEvent extends PostsEvent{

    private final DomainEventPublisher<PostsCancelledEvent> postsCancelledEventDomainEventPublisher;

    public PostsCancelledEvent(Post post,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<PostsCancelledEvent> postsCancelledEventDomainEventPublisher) {
        super(post, createdAt);
        this.postsCancelledEventDomainEventPublisher = postsCancelledEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        postsCancelledEventDomainEventPublisher.publish(this);
    }
}
