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
                notificationDTO.getType(),
                notificationDTO.getMessage(),
                notificationDTO.getTimestamp()
        );
        Notification savedNotification = notificationRepository.save(notification);

        return new NotificationDTO(
                savedNotification.getId(),
                savedNotification.getType(),
                savedNotification.getMessage(),
                savedNotification.getTimestamp()
        );
    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setType(notificationDTO.getType());
            notification.setMessage(notificationDTO.getMessage());
            notification.setTimestamp(notificationDTO.getTimestamp());
            Notification updatedNotification = notificationRepository.save(notification);

            return new NotificationDTO(
                    updatedNotification.getId(),
                    updatedNotification.getType(),
                    updatedNotification.getMessage(),
                    updatedNotification.getTimestamp()
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
                notification.getType(),
                notification.getMessage(),
                notification.getTimestamp()
        )).toList();
    }

    public NotificationDTO getNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.map(notification -> new NotificationDTO(
                notification.getId(),
                notification.getType(),
                notification.getMessage(),
                notification.getTimestamp()
        )).orElse(null);
    }
}
