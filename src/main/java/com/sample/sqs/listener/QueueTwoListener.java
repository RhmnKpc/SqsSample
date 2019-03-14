package com.sample.sqs.listener;

import com.sample.sqs.service.QueueTwoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueTwoListener {

    private final QueueTwoService queueTwoService;


    public QueueTwoListener(final QueueTwoService queueTwoService) {
        this.queueTwoService = queueTwoService;
    }

    @SqsListener(value = "queuetwo.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void listen(String message, String messageId) {
        log.info("Queue Two message received message : {}, messageId: {} ", message, messageId);
        queueTwoService.handle(message);
    }
}
