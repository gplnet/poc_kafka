package com.pichincha.poc.system.service.messaging.listener.kafka;

import com.pichincha.poc.system.kafka.consumer.KafkaConsumer;
import com.pichincha.poc.system.kafka.post.avro.model.PocResponseAvroModel;
import com.pichincha.poc.system.kafka.post.avro.model.PostsStatus;
import com.pichincha.poc.system.post.service.domain.exception.PostNotFoundException;
import com.pichincha.poc.system.service.domain.ports.input.message.listener.editorial.EditorialResponseMessageListener;
import com.pichincha.poc.system.service.messaging.mapper.PostMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class EditorialResponseKafkaListener implements KafkaConsumer<PocResponseAvroModel> {

    private final EditorialResponseMessageListener editorialResponseMessageListener;

    private final PostMessagingDataMapper postMessagingDataMapper;

    public EditorialResponseKafkaListener(EditorialResponseMessageListener editorialResponseMessageListener, PostMessagingDataMapper postMessagingDataMapper) {
        this.editorialResponseMessageListener = editorialResponseMessageListener;
        this.postMessagingDataMapper = postMessagingDataMapper;
    }

    @Override
    @KafkaListener(id= "${kafka-consumer-config.posts-consumer-group-id}", topics = "${post-service.posts-request-topic-name}")
    public void recieve(@Payload List<PocResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offset) {


        log.info("{} number of posts responses received with keys: {}, partitions: {} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offset.toString());

        log.info("aaaa: {}", messages.toString());

        messages.forEach(pocResponseAvroModel -> {

            try {
                if (PostsStatus.PENDING == pocResponseAvroModel.getPostsStatus()) {
                    log.info("Proccesing succesfull post for id: {}", pocResponseAvroModel.getId());
                    editorialResponseMessageListener.editorialCompleted(postMessagingDataMapper
                            .editorialResponseAvroModelToEditorialResponse(pocResponseAvroModel));
                } else if (PostsStatus.CANCELLED == pocResponseAvroModel.getPostsStatus()) {
                    log.info("Processing unsuccessful post for id: {}", pocResponseAvroModel.getId());
                    editorialResponseMessageListener.editorialCancelled(postMessagingDataMapper.editorialResponseAvroModelToEditorialResponse(pocResponseAvroModel));
                }

            }catch (DataAccessException e){
                log.error("No post found for post id: {}", pocResponseAvroModel.getId());
            } catch (PostNotFoundException e) {
                log.error("No post found for post id: {}", pocResponseAvroModel.getId());
            }
        });

    }
}
