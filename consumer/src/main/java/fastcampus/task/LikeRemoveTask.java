package fastcampus.task;

import fastcampus.domain.LikeNotification;
import fastcampus.domain.Notification;
import fastcampus.service.NotificationGetService;
import fastcampus.service.NotificationRemoveService;
import fastcampus.service.NotificationSaveService;
import fastcampus.domain.NotificationType;
import fastcampus.client.PostClient;
import fastcampus.event.LikeEvent;
import java.time.Instant;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LikeRemoveTask {

    @Autowired
    private PostClient postClient;

    @Autowired
    private NotificationGetService getService;

    @Autowired
    private NotificationRemoveService removeService;

    @Autowired
    private NotificationSaveService saveService;

    public void processEvent(LikeEvent event) {
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(
                NotificationType.LIKE, event.getPostId());

        if (optionalNotification.isEmpty()) {
            log.error("No notification with postId: {}", event.getPostId());
            return;
        }

        // likers에서 event.userId 제거 -> likers 가 비어있으면 알림 삭제 2. 알림 삭제
        LikeNotification notification = (LikeNotification) optionalNotification.get();
        removeLikerAndUpdateNotification(notification, event);
    }

    private void removeLikerAndUpdateNotification(LikeNotification notification, LikeEvent event) {
        notification.removeLiker(event.getUserId(), Instant.now());

        if (notification.getLikerIds().isEmpty()) {
            removeService.deleteById(notification.getId());
        }else{
            saveService.upsert(notification);
        }
    }


}
