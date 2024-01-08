package com.pichincha.poc.system.service.domain;

import com.pichincha.poc.system.service.domain.dto.create.CreatePostsCommand;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsResponse;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostQuery;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostResponse;
import com.pichincha.poc.system.service.domain.ports.input.service.PostsApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class PostsApplicationServiceImpl implements PostsApplicationService {

    private final PostsCreateCommandHandler postsCreateCommandHandler;
    private final PostTrackcommandHandler postTrackcommandHandler;


    public PostsApplicationServiceImpl(PostsCreateCommandHandler postsCreateCommandHandler, PostTrackcommandHandler postTrackcommandHandler) {
        this.postsCreateCommandHandler = postsCreateCommandHandler;

        this.postTrackcommandHandler = postTrackcommandHandler;
    }

    @Override
    public CreatePostsResponse createPost(CreatePostsCommand createPostsCommand) {
        return postsCreateCommandHandler.createPosts(createPostsCommand);
    }

    @Override
    public TrackPostResponse trackPost(TrackPostQuery trackPostQuery) {
        return postTrackcommandHandler.trackPost(trackPostQuery);
    }
}
