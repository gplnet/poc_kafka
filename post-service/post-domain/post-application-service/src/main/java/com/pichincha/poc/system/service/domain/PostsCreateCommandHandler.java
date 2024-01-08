package com.pichincha.poc.system.service.domain;

import com.pichincha.poc.system.post.service.domain.event.PostsCreatedEvent;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsCommand;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsResponse;
import com.pichincha.poc.system.service.domain.mapper.PostsDataMapper;
import com.pichincha.poc.system.service.domain.ports.output.message.publisher.PostsCreatedEditorialRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PostsCreateCommandHandler {

    private final PostsCreateHelper postsCreateHelper;
    private final PostsDataMapper postsDataMapper;

    private final PostsCreatedEditorialRequestMessagePublisher postsCreatedRequestMessagePublisher;

    public PostsCreateCommandHandler(PostsCreateHelper postsCreateHelper,
                                     PostsDataMapper postsDataMapper,
                                     PostsCreatedEditorialRequestMessagePublisher postsCreatedRequestMessagePublisher) {
        this.postsCreateHelper = postsCreateHelper;
        this.postsDataMapper = postsDataMapper;
        this.postsCreatedRequestMessagePublisher = postsCreatedRequestMessagePublisher;
    }


    public CreatePostsResponse createPosts(CreatePostsCommand createPostsCommand){

        PostsCreatedEvent postsCreatedEvent = postsCreateHelper.persistPosts(createPostsCommand);
        log.info("post created with id: {}", postsCreatedEvent.getPost().getId().toString());
        postsCreatedRequestMessagePublisher.publish(postsCreatedEvent);
        return postsDataMapper.postToCreatePostPostResponse(postsCreatedEvent.getPost(), "Post created successfully");
    }
}
