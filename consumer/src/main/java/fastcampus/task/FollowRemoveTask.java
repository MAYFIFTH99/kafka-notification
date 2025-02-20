package fastcampus.task;

import fastcampus.FollowNotification;
import fastcampus.Notification;
import fastcampus.NotificationGetService;
import fastcampus.NotificationRemoveService;
import fastcampus.NotificationType;
import fastcampus.event.FollowEvent;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowRemoveTask {


    private final NotificationGetService getService;

    private final NotificationRemoveService removeService;

    public void processEvent(FollowEvent event){
        Optional<Notification> notification = getService.getNotificationByTypeAndFollowerId(
                NotificationType.FOLLOW, event.getTargetUserId(), event.getUserId());

        if(notification.isPresent()){
            FollowNotification followNotification = (FollowNotification) notification.get();
            removeService.deleteById(followNotification.getId());
        }
        else{
            log.error("Notification not found. targetUserId: {}", event.getTargetUserId());
            return;
        }

    }

}
