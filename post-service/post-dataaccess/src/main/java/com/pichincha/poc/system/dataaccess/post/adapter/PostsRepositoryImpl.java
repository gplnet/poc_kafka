package com.pichincha.poc.system.dataaccess.post.adapter;

import com.pichincha.poc.system.dataaccess.post.respository.PostMongoRepository;

import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.service.domain.ports.output.repository.PostsRepository;
import org.springframework.stereotype.Component;
import com.pichincha.poc.system.dataaccess.post.mapper.PostsDataAccesMapper;

import java.util.Optional;
@Component
public class PostsRepositoryImpl implements PostsRepository {

    private final PostMongoRepository postMongoRepository;
    private final PostsDataAccesMapper postsDataAccesMapper;

    public PostsRepositoryImpl(PostMongoRepository postMongoRepository,
                               PostsDataAccesMapper postsDataAccesMapper) {
        this.postMongoRepository = postMongoRepository;
        this.postsDataAccesMapper = postsDataAccesMapper;
    }

    @Override
    public Optional<Post> findPosts(Integer id) {
        return postMongoRepository.findById(id).map(postsDataAccesMapper::postEntityToPost);
    }

    @Override
    public Post save(Post post) {
        return postsDataAccesMapper.postEntityToPost(postMongoRepository
                .save(postsDataAccesMapper.postToPostEntiy(post)));
    }
}
