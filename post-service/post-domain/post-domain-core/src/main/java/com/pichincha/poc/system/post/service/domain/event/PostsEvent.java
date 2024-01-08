package com.pichincha.poc.system.post.service.domain.event;

import com.pichincha.poc.system.domain.event.DomainEvent;
import com.pichincha.poc.system.post.service.domain.entity.Post;

import java.time.ZonedDateTime;

public abstract class PostsEvent implements DomainEvent<Post> {
    private final Post post;
    private final ZonedDateTime createdAt;


    public PostsEvent(Post post, ZonedDateTime createdAt) {
        this.post = post;
        this.createdAt = createdAt;

    }

    public Post getPost() {
        return post;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }


}
