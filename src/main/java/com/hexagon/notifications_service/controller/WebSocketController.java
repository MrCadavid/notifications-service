package com.hexagon.notifications_service;
import com.hexagon.notifications_service.dto.NotificationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public NotificationDTO sendNotification(NotificationDTO notification) throws Exception {
        return notification;
    }
}
