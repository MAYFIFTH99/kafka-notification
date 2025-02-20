package fastcampus.service;

import fastcampus.domain.Notification;
import fastcampus.repository.NotificationRepository;
import fastcampus.domain.NotificationType;
import java.time.Instant;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationGetService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId) {
        return notificationRepository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType type, Long postId) {
        return notificationRepository.findByTypeAndPostId(type, postId);
    }

    public Optional<Notification> getNotificationByTypeAndFollowerId(NotificationType type, Long userId, Long followerId) {
        return notificationRepository.findByTypeAndTargetUserId(type, userId, followerId);
    }

    public Instant getLatestUpdatedAt(Long userId){
        Optional<Notification> notification = notificationRepository.findFirstByUserIdOrderByLastUpdatedAtDesc(
                userId);
        if(notification.isEmpty()){
            return null;
        }
        return notification.get().getLastUpdatedAt();
    }
}
