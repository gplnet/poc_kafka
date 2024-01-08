package com.pichincha.poc.system.post.service.application.rest;

import com.pichincha.poc.system.service.domain.dto.create.CreatePostsCommand;
import com.pichincha.poc.system.service.domain.dto.create.CreatePostsResponse;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostQuery;
import com.pichincha.poc.system.service.domain.dto.create.TrackPostResponse;
import com.pichincha.poc.system.service.domain.ports.input.service.PostsApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/posts", produces = "application/vnd.api.v1+json")
public class PostController {

    private final PostsApplicationService postsApplicationService;

    public PostController(PostsApplicationService postsApplicationService) {
        this.postsApplicationService = postsApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreatePostsResponse> createPost(@RequestBody  CreatePostsCommand createPostsCommand){
        log.info("Creating post for userId: {} at title: {}", createPostsCommand.getUserId(), createPostsCommand.getTitle());
        CreatePostsResponse createPostsResponse = postsApplicationService.createPost(createPostsCommand);
        log.info("Post created with id: {}", createPostsResponse.getId());
        return ResponseEntity.ok(createPostsResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrackPostResponse> getLola(@PathVariable String id){
        log.info("Post get with id: {}", id);
        TrackPostResponse trackPostsResponse = postsApplicationService.trackPost(TrackPostQuery.builder().postTrackId(Integer.parseInt(id)).build());
        log.info("Returning Post get with id: {}", trackPostsResponse.getId());
        return ResponseEntity.ok(trackPostsResponse);
    }



}
