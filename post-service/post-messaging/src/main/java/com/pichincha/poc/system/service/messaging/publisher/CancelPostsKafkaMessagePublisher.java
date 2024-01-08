package com.pichincha.poc.system.service.messaging.publisher;

import com.pichincha.poc.system.kafka.post.avro.model.PocRequestAvroModel;
import com.pichincha.poc.system.kafka.producer.service.KafkaMessageHelper;
import com.pichincha.poc.system.kafka.producer.service.KafkaProducer;
import com.pichincha.poc.system.post.service.domain.event.PostsCancelledEvent;
import com.pichincha.poc.system.service.domain.config.PostsServiceConfigData;
import com.pichincha.poc.system.service.domain.ports.output.message.publisher.PostsCancelledEditorialRequestMessagePublisher;
import com.pichincha.poc.system.service.messaging.mapper.PostMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CancelPostsKafkaMessagePublisher implements PostsCancelledEditorialRequestMessagePublisher {

    private final PostMessagingDataMapper postMessagingDataMapper;
    private final PostsServiceConfigData postsServiceConfigData;
    private final  KafkaProducer<String, PocRequestAvroModel> kafkaProducer;
    private final PostKafkaMessageHelper postKafkaMessageHelper;

    public CancelPostsKafkaMessagePublisher(PostMessagingDataMapper postMessagingDataMapper,
                                            PostsServiceConfigData postsServiceConfigData,
                                            KafkaProducer<String, PocRequestAvroModel> kafkaProducer,
                                            KafkaMessageHelper kafkaMessageHelper, PostKafkaMessageHelper postKafkaMessageHelper) {
        this.postMessagingDataMapper = postMessagingDataMapper;
        this.postsServiceConfigData = postsServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.postKafkaMessageHelper = postKafkaMessageHelper;

    }

    @Override
    public void publish(PostsCancelledEvent domainEvent) {
        String postId =  domainEvent.getPost().getId().toString();
        log.info("Received postCancelledEvent for order id: {}", postId);
        try {
            PocRequestAvroModel pocRequestAvroModel = postMessagingDataMapper.postCancelledEventToRequestAvroModel(domainEvent);
            kafkaProducer.send(postsServiceConfigData.getPostsRequestTopicName(),
                    postId,
                    pocRequestAvroModel,
                    postKafkaMessageHelper.getKafkaCallback(postsServiceConfigData.getPostsResponseTopicName(),
                                                        pocRequestAvroModel,
                                                        postId,
                                            "PocRequestAvroModel"));
            log.info("PocRequestAvroModel sent to Kafka for post id: {}", pocRequestAvroModel.getTitle());
        }catch (Exception e){
            log.error("Error while sending PaymentRequestAvroModel message" + "to kafka with order id: {}, error: {}", postId, e.getMessage());
        }

    }
}
