package com.pichincha.poc.system.service.domain;

import com.pichincha.poc.system.post.service.domain.PostDomainService;
import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.post.service.domain.event.PostsCreatedEvent;
import com.pichincha.poc.system.post.service.domain.exception.PostDomainException;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsCommand;
import com.pichincha.poc.system.service.domain.mapper.PostsDataMapper;
import com.pichincha.poc.system.service.domain.ports.output.message.publisher.PostsCreatedEditorialRequestMessagePublisher;
import com.pichincha.poc.system.service.domain.ports.output.repository.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PostsCreateHelper {

    private final PostDomainService postDomainService;

    private final PostsRepository postsRepository;

    private final PostsDataMapper postsDataMapper;

    private final PostsCreatedEditorialRequestMessagePublisher postsCreatedRequestMessagePublisher;

    public PostsCreateHelper(PostDomainService postDomainService,
                             PostsRepository postsRepository,
                             PostsDataMapper postsDataMapper,
                             PostsCreatedEditorialRequestMessagePublisher postsCreatedRequestMessagePublisher) {
        this.postDomainService = postDomainService;
        this.postsRepository = postsRepository;
        this.postsDataMapper = postsDataMapper;
        this.postsCreatedRequestMessagePublisher = postsCreatedRequestMessagePublisher;
    }
    @Transactional
    public PostsCreatedEvent persistPosts(CreatePostsCommand createPostsCommand){
        Post post = postsDataMapper.createPostsCommandToPosts(createPostsCommand);
        PostsCreatedEvent postsCreatedEvent = postDomainService.validateAndInitiatePost(post, postsCreatedRequestMessagePublisher);
        savePost(post);
        log.info("Post is created with id: {}", postsCreatedEvent.getPost().getId().toString());
        return postsCreatedEvent;
    }

    private Post savePost(Post post){
        Post postRestult = postsRepository.save(post);
        if(postRestult == null){
            log.error("Could not save order!");
            throw new PostDomainException("Could not save order!");
        }
        log.info("Post is saved with id: {}", postRestult.getId().toString());
        return postRestult;
    }
}
