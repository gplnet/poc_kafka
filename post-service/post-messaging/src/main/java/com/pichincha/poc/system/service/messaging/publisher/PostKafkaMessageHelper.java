package com.pichincha.poc.system.service.messaging.publisher;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class PostKafkaMessageHelper {

    public <T> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String paymentResponseTopicName,
                                                                             T requestAvroModel,
                                                                             String postId, String requestAvroModelName) {

        return new BiConsumer<SendResult<String, T>, Throwable>() {
            @Override
            public void accept(SendResult<String, T> result, Throwable throwable) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successfull response from kafka for order id: {}" + "Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        postId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }


        };
    }

}
