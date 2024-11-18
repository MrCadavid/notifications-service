package com.hexagon.notifications_service.consumer;

import com.hexagon.notifications_service.dto.NotificationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RabbitMQJsonConsumer {

    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public RabbitMQJsonConsumer(ObjectMapper objectMapper, SimpMessagingTemplate messagingTemplate) {
        this.objectMapper = objectMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "notifications")
    public void receiveNotification(String notification) {
        try {
            // convert message string to dto 
            NotificationDTO notificationDTO = objectMapper.readValue(notification, NotificationDTO.class);
            
            // send notification to all suscriptors
            messagingTemplate.convertAndSend("/topic/notifications", notificationDTO);

            System.out.println("send notification " + notificationDTO);
        } catch (Exception e) {
            System.err.println("error process notification" + e.getMessage());
        }
    }
}
