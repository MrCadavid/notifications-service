package com.hexagon.notifications_service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO implements Serializable {

    private Long id; 
    private String type;
    private String message;
    private LocalDateTime timestamp;
    private Long eventId; 

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", eventId=" + eventId +
                '}';
    }
}
