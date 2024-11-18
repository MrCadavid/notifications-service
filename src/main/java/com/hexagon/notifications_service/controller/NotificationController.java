package com.hexagon.notifications_service.controller;

import com.hexagon.notifications_service.dto.NotificationDTO;
import com.hexagon.notifications_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {  

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint to create a new notification
    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO); 
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    // Endpoint to update an existing notification
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) { 
        NotificationDTO updatedNotification = notificationService.updateNotification(id, notificationDTO); 
        if (updatedNotification != null) {
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) { 
        boolean isDeleted = notificationService.deleteNotification(id); 
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to get the list of notifications
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {  
        List<NotificationDTO> notifications = notificationService.getAllNotifications();  
        return new ResponseEntity<>(notifications, HttpStatus.OK); 
    }

    // Endpoint to get notifications by event ID
@GetMapping("/event/{eventId}")
public ResponseEntity<List<NotificationDTO>> getNotificationsByEventId(@PathVariable Long eventId) {
    List<NotificationDTO> notifications = notificationService.getNotificationsByEventId(eventId);
    if (!notifications.isEmpty()) {
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

}
