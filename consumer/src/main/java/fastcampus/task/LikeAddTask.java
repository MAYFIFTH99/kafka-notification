package fastcampus.task;

import fastcampus.domain.LikeNotification;
import fastcampus.domain.Notification;
import fastcampus.service.NotificationGetService;
import fastcampus.utils.NotificationIdGenerator;
import fastcampus.service.NotificationSaveService;
import fastcampus.domain.NotificationType;
import fastcampus.domain.Post;
import fastcampus.client.PostClient;
import fastcampus.event.LikeEvent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LikeAddTask {

    @Autowired
    private PostClient postClient;

    @Autowired
    private NotificationGetService getService;

    @Autowired
    private NotificationSaveService saveService;

    public void processEvent(LikeEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (post == null) {
            log.error("Post is null with postId: {}", event.getPostId());
            return;
        }
        if(post.getUserId().equals(event.getUserId())){
            return;
        }

        // like notification 1. 신규 생성, 2. 업데이트 ->  like notification db 저장
        saveService.upsert(createOrUpdateNotification(post, event));
    }

    private Notification createOrUpdateNotification(Post post, LikeEvent event){
        Optional<Notification> optionalNotification = getService.getNotificationByTypeAndPostId(
                NotificationType.LIKE, post.getId());

        Instant now = Instant.now();
        Instant retention = now.plus(90, ChronoUnit.DAYS);

        if(optionalNotification.isPresent()){
            // update
            return updateNotification((LikeNotification) optionalNotification.get(),event,now, retention);
        }
        else{
            // create
            return createNotification(post, event, now, retention);

        }

    }

    private Notification updateNotification(LikeNotification notification, LikeEvent event, Instant now, Instant retention){
        notification.addLiker(event.getUserId(),event.getCreatedAt(),now, retention);
        return notification;
    }

    private Notification createNotification(Post post, LikeEvent event, Instant now, Instant retention){
        return new LikeNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.LIKE,
                event.getCreatedAt(),
                now,
                now,
                retention,
                post.getId(),
                List.of(event.getUserId())
        );
    }
}
