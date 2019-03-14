package com.sample.sqs.service;

import com.sample.sqs.message.QueueMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class QueueTwoService {

    private final SqsMessageQueueService sqsMessageQueueService;
    private final String queueName = "queuetwo.fifo";

    public QueueTwoService(final SqsMessageQueueService sqsMessageQueueService) {
        this.sqsMessageQueueService = sqsMessageQueueService;
    }

    public void handle(String message) {
        log.info("Handling started for queue two ,message: {}", message);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Handling stop for queue two");
    }

    public void send() {
        QueueMessage queueMessage = new QueueMessage();
        queueMessage.setId(UUID.randomUUID().toString().replace("-", ""));
        queueMessage.setMessage("Example Message For Queue Two");
        sqsMessageQueueService.sendMessage(queueName, queueMessage);
    }
}
