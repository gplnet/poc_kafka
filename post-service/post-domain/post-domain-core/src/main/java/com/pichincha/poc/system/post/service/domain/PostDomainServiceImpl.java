package com.pichincha.poc.system.post.service.domain;

import com.pichincha.poc.system.domain.event.publisher.DomainEventPublisher;
import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.post.service.domain.event.PostsCancelledEvent;
import com.pichincha.poc.system.post.service.domain.event.PostsCreatedEvent;
import com.pichincha.poc.system.post.service.domain.event.PostsEvent;
import com.pichincha.poc.system.post.service.domain.event.PostsInReviewEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class PostDomainServiceImpl implements PostDomainService{

    private static final String UTC = "UTC";

    @Override
    public PostsCreatedEvent validateAndInitiatePost(Post post, DomainEventPublisher<PostsCreatedEvent> postCreatedEventDomainEventPublisher) {
        post.initializePosts();
        log.info("POSTs with id: {} is initiated", post.getId().toString());
        return new PostsCreatedEvent(post, ZonedDateTime.now(ZoneId.of(UTC)), postCreatedEventDomainEventPublisher);
    }

    @Override
    public PostsInReviewEvent reviewPosts(Post post, DomainEventPublisher<PostsInReviewEvent> postsInReviewEventDomainEventPublisher) {
        post.review();
        log.info("Post with id: {} is review", post.getId().toString());
        return new PostsInReviewEvent(post,  ZonedDateTime.now(ZoneId.of(UTC)),postsInReviewEventDomainEventPublisher);
    }

    @Override
    public void approvePost(Post post) {
        post.approve();
        log.info("Post with id: {} is approved", post.getId().toString());

    }

    @Override
    public PostsCancelledEvent cancelPostsRewiew(Post post, List<String> failureMessages, DomainEventPublisher<PostsCancelledEvent> postsCancelledEventDomainEventPublisher) {
        post.initCancel(failureMessages);
        log.info("Post review is cancelled for post  id: {}", post.getId().toString());
        return new PostsCancelledEvent(post,ZonedDateTime.now(ZoneId.of(UTC)),postsCancelledEventDomainEventPublisher);
    }

    @Override
    public void cancelPost(Post post, List<String> failureMessages) {
        post.cancel(failureMessages);
        log.info("Post with id: {} is cancelled", post.getId().toString());
    }
}
