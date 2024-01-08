package com.pichincha.poc.system.post.service.domain;

import com.pichincha.poc.system.domain.event.publisher.DomainEventPublisher;
import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.post.service.domain.event.PostsCancelledEvent;
import com.pichincha.poc.system.post.service.domain.event.PostsCreatedEvent;
import com.pichincha.poc.system.post.service.domain.event.PostsInReviewEvent;

import java.util.List;

public interface PostDomainService {

    PostsCreatedEvent validateAndInitiatePost(Post post, DomainEventPublisher<PostsCreatedEvent> postCreatedEventDomainEventPublisher);
    PostsInReviewEvent reviewPosts(Post post, DomainEventPublisher<PostsInReviewEvent> postsInReviewEventDomainEventPublisher);

    void approvePost(Post post);

    PostsCancelledEvent cancelPostsRewiew(Post post, List<String> failureMessages, DomainEventPublisher<PostsCancelledEvent> postsCancelledEventDomainEventPublisher);

    void cancelPost(Post post, List<String> failureMessages);


}
