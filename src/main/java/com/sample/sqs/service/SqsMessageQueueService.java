package com.sample.sqs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.sample.sqs.message.QueueMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SqsMessageQueueService {

    private final QueueMessagingTemplate queueMessagingTemplate;

    public SqsMessageQueueService(@Qualifier("amazonSQSAsync") final AmazonSQSAsync amazonSQS) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQS);
    }

    public void sendMessage(String queueName, QueueMessage queueMessage) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, UUID.randomUUID().toString().replace("-", ""));
        headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, queueMessage.getId().concat("-").concat(UUID.randomUUID().toString().replace("-", "")));
        queueMessagingTemplate.convertAndSend(queueName, queueMessage, headers);
    }
}
