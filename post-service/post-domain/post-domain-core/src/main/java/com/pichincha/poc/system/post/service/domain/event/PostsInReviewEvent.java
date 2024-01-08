package com.pichincha.poc.system.post.service.domain.event;

import com.pichincha.poc.system.domain.event.publisher.DomainEventPublisher;
import com.pichincha.poc.system.post.service.domain.entity.Post;

import java.time.ZonedDateTime;

public class PostsInReviewEvent extends PostsEvent {

    private final DomainEventPublisher<PostsInReviewEvent> postsInReviewEventDomainEventPublisher;

    public PostsInReviewEvent(Post post, ZonedDateTime createdAt,
                              DomainEventPublisher<PostsInReviewEvent> postsInReviewEventDomainEventPublisher) {
        super(post, createdAt);
        this.postsInReviewEventDomainEventPublisher = postsInReviewEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        postsInReviewEventDomainEventPublisher.publish(this);

    }
}
