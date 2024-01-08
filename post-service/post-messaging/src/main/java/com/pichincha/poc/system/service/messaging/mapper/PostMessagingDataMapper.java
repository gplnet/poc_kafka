package com.pichincha.poc.system.service.messaging.mapper;

import com.pichincha.poc.system.kafka.post.avro.model.PocRequestAvroModel;
import com.pichincha.poc.system.kafka.post.avro.model.PocResponseAvroModel;
import com.pichincha.poc.system.kafka.post.avro.model.PostsStatus;
import com.pichincha.poc.system.post.service.domain.entity.Post;
import com.pichincha.poc.system.post.service.domain.event.PostsCancelledEvent;
import com.pichincha.poc.system.post.service.domain.event.PostsCreatedEvent;
import com.pichincha.poc.system.service.domain.dto.message.EditorialResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMessagingDataMapper {

    public PocRequestAvroModel postCreatedEventToRequestAvroModel(PostsCreatedEvent postsCreatedEvent){
        Post post = postsCreatedEvent.getPost();
        return  PocRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setBody(post.getBody())
                .setTitle(post.getTitle())
                .setUserId(UUID.randomUUID())
                .setCreatedAt(postsCreatedEvent.getCreatedAt().toInstant())
                .setFailureMessages(post.getFailureMensagges())
                .setPostsStatus(PostsStatus.PENDING)
                .build();

    }

    public PocRequestAvroModel postCancelledEventToRequestAvroModel(PostsCancelledEvent postsCancelledEvent){
        Post post = postsCancelledEvent.getPost();
        return  PocRequestAvroModel.newBuilder()
                .setBody(post.getBody())
                .setTitle(post.getTitle())
                .setUserId(UUID.randomUUID())
                .setCreatedAt(postsCancelledEvent.getCreatedAt().toInstant())
                .setPostsStatus(PostsStatus.CANCELLED)
                .build();

    }
    public EditorialResponse editorialResponseAvroModelToEditorialResponse(PocResponseAvroModel responseAvroModel){
        return EditorialResponse.builder()
                .id(responseAvroModel.getId().toString())
                .userId(responseAvroModel.getUserId().toString())
                .createdAt(responseAvroModel.getCreatedAt())
                .postsStatus(com.pichincha.poc.system.domain.valueobject.PostsStatus.valueOf(responseAvroModel.getPostsStatus().name()))
                .failureMessages(responseAvroModel.getFailureMessages())
                .build();
    }
}
