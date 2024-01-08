package com.pichincha.poc.system.dataaccess.post.mapper;

import com.pichincha.poc.system.dataaccess.post.entity.PostsEntity;

import com.pichincha.poc.system.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostsDataAccesMapper {

    public PostsEntity postToPostEntiy(Post post){
        PostsEntity postsEntity = PostsEntity.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .postsStatus(post.getPostsStatus())
                .failureMensagges(post.getFailureMensagges())
                .build();
        return postsEntity;
    }

    public Post postEntityToPost(PostsEntity postsEntity){
        return Post.builder()
                .id(postsEntity.getId())
                .title(postsEntity.getTitle())
                .body(postsEntity.getBody())
                .postsStatus(postsEntity.getPostsStatus())
                .failureMensagges(postsEntity.getFailureMensagges())
                .build();
    }
}
