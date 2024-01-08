package com.pichincha.poc.system.service.domain;

import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.post.service.domain.exception.PostNotFoundException;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostQuery;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostResponse;
import com.pichincha.poc.system.service.domain.mapper.PostsDataMapper;
import com.pichincha.poc.system.service.domain.ports.output.repository.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class PostTrackcommandHandler {

    private final PostsDataMapper postsDataMapper;
    private final PostsRepository postsRepository;

    public PostTrackcommandHandler(PostsDataMapper postsDataMapper, PostsRepository postsRepository) {
        this.postsDataMapper = postsDataMapper;
        this.postsRepository = postsRepository;
    }

    public TrackPostResponse trackPost(TrackPostQuery trackPostQuery){
        Optional<Post> postResult = postsRepository.findPosts(trackPostQuery.getPostTrackId());
        if(postResult.isEmpty()){
            log.warn("Could not find post with tracking id: {}", trackPostQuery.getPostTrackId());
            throw new PostNotFoundException("Could not find post with tracking id: " + trackPostQuery.getPostTrackId());
        }
        return postsDataMapper.postToTrackPostResponse(postResult.get(), "OK");
    }
}
