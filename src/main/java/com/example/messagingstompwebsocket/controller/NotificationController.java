package com.example.messagingstompwebsocket;
import com.example.messagingstompwebsocket.dto.NotificationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public NotificationDTO sendNotification(NotificationDTO notification) throws Exception {
        return notification;
    }
}
