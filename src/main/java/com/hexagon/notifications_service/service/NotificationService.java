package com.hexagon.notifications_service.service;

import com.hexagon.notifications_service.dto.NotificationDTO;
import com.hexagon.notifications_service.entity.Notification;
import com.hexagon.notifications_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification(
                null,
                notificationDTO.getEventId(),
                notificationDTO.getMessage(),
                notificationDTO.getSentAt()
        );
        Notification savedNotification = notificationRepository.save(notification);

        return new NotificationDTO(
                savedNotification.getId(),
                savedNotification.getEventId(),
                savedNotification.getMessage(),
                savedNotification.getSentAt()
        );
    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setEventId(notificationDTO.getEventId());
            notification.setMessage(notificationDTO.getMessage());
            notification.setSentAt(notificationDTO.getSentAt());
            Notification updatedNotification = notificationRepository.save(notification);

            return new NotificationDTO(
                    updatedNotification.getId(),
                    updatedNotification.getEventId(),
                    updatedNotification.getMessage(),
                    updatedNotification.getSentAt()
            );
        }
        return null;
    }

    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map(notification -> new NotificationDTO(
                notification.getId(),
                notification.getEventId(),
                notification.getMessage(),
                notification.getSentAt()
        )).toList();
    }

    public NotificationDTO getNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.map(notification -> new NotificationDTO(
                notification.getId(),
                notification.getEventId(),
                notification.getMessage(),
                notification.getSentAt()
        )).orElse(null);
    }

    public List<NotificationDTO> getNotificationsByEventId(Long eventId) {
        return notificationRepository.findByEventId(eventId).stream()
                .map(notification -> new NotificationDTO(
                        notification.getId(),
                        notification.getEventId(),
                        notification.getMessage(),
                        notification.getSentAt()
                ))
                .toList();
    }
}
