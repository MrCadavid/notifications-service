package com.hexagon.notifications_service.repository;

import com.hexagon.notifications_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEvent_Id(Long eventId);
}
