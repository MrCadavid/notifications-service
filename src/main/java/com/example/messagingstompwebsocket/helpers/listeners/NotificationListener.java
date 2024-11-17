package com.example.messagingstompwebsocket;
import com.example.messagingstompwebsocket.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationListener {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "notifications")
    public void receiveNotification(NotificationDTO notification) {
         System.out.println(notification);
        // messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
