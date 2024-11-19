package com.hexagon.notifications_service.service;


import com.hexagon.notifications_service.entity.Notification;
import com.hexagon.notifications_service.entity.Event;
import com.hexagon.notifications_service.repository.NotificationRepository;
import com.hexagon.notifications_service.dto.NotificationDTO;
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

    
    private NotificationDTO toDTO(Notification notification) {
        return new NotificationDTO(
                notification.getId(),
                notification.getMessage(),
                notification.getEvent() != null ? notification.getEvent().getId() : null,
                notification.getSentAt()
        );
    }


    private Notification toEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setId(notificationDTO.getId());
        notification.setMessage(notificationDTO.getMessage());
    
        if (notificationDTO.getEventId() != null) {
            Event event = new Event();
            event.setId(notificationDTO.getEventId());
            notification.setEvent(event);
        }
        notification.setSentAt(notificationDTO.getSentAt());
        return notification;
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = toEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return toDTO(savedNotification);
    }

    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<NotificationDTO> getNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.map(this::toDTO);
    }

    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification existingNotification = notificationOptional.get();
            existingNotification.setMessage(notificationDTO.getMessage());
            if (notificationDTO.getEventId() != null) {
                Event event = new Event();
                event.setId(notificationDTO.getEventId());
                existingNotification.setEvent(event);
            } else {
                existingNotification.setEvent(null);
            }
            existingNotification.setSentAt(notificationDTO.getSentAt());
            Notification updatedNotification = notificationRepository.save(existingNotification);
            return toDTO(updatedNotification);
        }
        return null;
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<NotificationDTO> getNotificationsByEventId(Long eventId) {
        List<Notification> notifications = notificationRepository.findByEvent_Id(eventId);
        return notifications.stream()
                .map(this::toDTO)
                .toList();
    }
}
