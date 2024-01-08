package com.pichincha.poc.system.service.domain.ports.input.service;

import com.pichincha.poc.system.service.domain.dto.create.CreatePostsCommand;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsResponse;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostQuery;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostResponse;
import jakarta.validation.Valid;

public interface PostsApplicationService {
    CreatePostsResponse createPost(@Valid CreatePostsCommand createPostsCommand);
    TrackPostResponse trackPost(@Valid TrackPostQuery trackPostQuery);
}
