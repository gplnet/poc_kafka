package com.pichincha.poc.system.service.domain.mapper;

import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsCommand;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsResponse;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostResponse;
import org.springframework.stereotype.Component;

@Component
public class PostsDataMapper {

    public Post createPostsCommandToPosts(CreatePostsCommand createPostsCommand){
        return Post.builder()
                .id(createPostsCommand.getId())
                .title(createPostsCommand.getTitle())
                .body(createPostsCommand.getBody())
                .build();
    }

    public CreatePostsResponse postToCreatePostPostResponse(Post post, String message){
        return CreatePostsResponse.builder()
                .id(post.getId())
                .message(message)
                .build();
    }

    public TrackPostResponse postToTrackPostResponse(Post post, String message){
        return TrackPostResponse.builder()
                .id(post.getId())
                .postsStatus(post.getPostsStatus())
                .message(message)
                .build();
    }
}
