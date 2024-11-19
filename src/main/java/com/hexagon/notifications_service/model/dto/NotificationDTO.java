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
    private Long eventId;
    private String message;
    private LocalDateTime sentAt; 

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", message='" + message + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
