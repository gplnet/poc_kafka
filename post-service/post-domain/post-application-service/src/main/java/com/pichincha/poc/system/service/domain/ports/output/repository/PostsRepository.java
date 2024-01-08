package com.pichincha.poc.system.service.domain.ports.output.repository;

import com.pichincha.poc.system.post.service.domain.entity.Post;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostsRepository {


    Optional<Post> findPosts(Integer id);
    Post save(Post post);
}
