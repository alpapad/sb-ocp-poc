package com.apapadak.poc.kafka.receiver;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

import com.apapadak.poc.kafka.KafkaTopics;
import com.apapadak.poc.kafka.payload.KafkaPayload;

@Configuration
public class KafkaReceiverConfiguration {
    
    private final Logger logger = LoggerFactory.getLogger(KafkaReceiverConfiguration.class);

    private final TaskExecutor exec = new SimpleAsyncTaskExecutor();

    /*
     * Boot will autowire this into the container factory.
     */
    @Bean
    DefaultErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
    }

    @Bean
    RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @KafkaListener(id = "receiverGroup", topics = KafkaTopics.FROM_GRPC, concurrency = "1")
    public void listen(ConsumerRecord<String,KafkaPayload> payload) {
        logger.info("Received:{},{} --> {} ", payload.key(), payload.value(), payload.headers());
        if (payload.value().getEmpId().startsWith("fail")) {
            throw new RuntimeException("failed");
        }
        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

    @KafkaListener(id = "dltGroup", topics = KafkaTopics.FROM_GRPC_DLT, concurrency = "1")
    public void dltListen(String in) {
        logger.info("Received from DLT: " + in);
        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

    @Bean
    NewTopic consumerTopic() {
        return new NewTopic(KafkaTopics.FROM_GRPC, 10, (short) 1);
    }

    @Bean
    NewTopic cosumerDltTopic() {
        return new NewTopic(KafkaTopics.FROM_GRPC_DLT, 10, (short) 1);
    }
}
