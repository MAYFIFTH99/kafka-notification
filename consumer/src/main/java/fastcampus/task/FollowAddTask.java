package fastcampus.task;

import fastcampus.domain.FollowNotification;
import fastcampus.utils.NotificationIdGenerator;
import fastcampus.service.NotificationSaveService;
import fastcampus.domain.NotificationType;
import fastcampus.event.FollowEvent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FollowAddTask {

    private final NotificationSaveService saveService;

    public void processEvent(FollowEvent event){

        saveService.insert(createFollowNotification(event));
    }

    private FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();
        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }

}
