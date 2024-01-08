package com.pichincha.poc.system.kafka.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.poc.system.outbox.OutboxStatus;
import com.pichincha.poc.system.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T getPostEventPayload(String payload, Class<T> outputType){
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            log.error("Could not read {} object", outputType.getName());
            throw new PostDomainException("Could not read {} object"+ outputType.getName() + "object!",e);
        }
    }
    public <T, U> BiConsumer<SendResult<String, T>, Throwable>getKafkaCallBack(String reesponseTopicName, T avroModel, U outboxMessage,
                                                                               BiConsumer<U, OutboxStatus> outboxCallBack,
                                                                               String postId, String avroModelName){
        return(result, ex) -> {
            if(ex == null) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                log.info("Received succesfull response from kasfka for order id: {}" +
                        "Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        postId,
                        recordMetadata.topic(),
                        recordMetadata.partition(),
                        recordMetadata.offset(),
                        recordMetadata.timestamp());
                outboxCallBack.accept(outboxMessage, OutboxStatus.COMPLETED);
            }else{
                log.error("Error while sending {} with message: {} and outbox type: {} to topic {}: to topic{}",
                        avroModelName, avroModel.toString(), outboxMessage.getClass().getName(), reesponseTopicName, ex);
                outboxCallBack.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }
}
