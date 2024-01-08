package com.pichincha.poc.system.dataaccess.post.respository;


import com.pichincha.poc.system.post.service.domain.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.pichincha.poc.system.dataaccess.post.entity.PostsEntity;


import java.util.Optional;

@Repository
public interface PostMongoRepository  extends MongoRepository<PostsEntity, Integer> {
    Optional<PostsEntity> findByTitle(String title);
}
