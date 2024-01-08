package com.pichincha.poc.system.post.service.domain.event;

import com.pichincha.poc.system.domain.event.publisher.DomainEventPublisher;
import com.pichincha.poc.system.post.service.domain.entity.Post;

import java.time.ZonedDateTime;

public class PostsCreatedEvent extends PostsEvent {

    private final DomainEventPublisher<PostsCreatedEvent> postsCreatedEventDomainEventPublisher;

    public PostsCreatedEvent(Post post,
                             ZonedDateTime createdAt,
                             DomainEventPublisher<PostsCreatedEvent> postsCreatedEventDomainEventPublisher) {
        super(post, createdAt);
        this.postsCreatedEventDomainEventPublisher = postsCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        postsCreatedEventDomainEventPublisher.publish(this);
    }
}
