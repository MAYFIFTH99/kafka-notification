package fastcampus.response;

import fastcampus.domain.NotificationType;
import fastcampus.service.dto.ConvertedCommentNotification;
import fastcampus.service.dto.ConvertedFollowNotification;
import fastcampus.service.dto.ConvertedLikeNotification;
import fastcampus.service.dto.ConvertedNotification;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class UserNotificationResponse {
    private String id;
    private NotificationType type;
    private Instant occurredAt;

    public static UserNotificationResponse of(ConvertedNotification notification) {
        switch(notification.getType()){
            case COMMENT -> { return CommentUserNotificationResponse.of((ConvertedCommentNotification) notification);}
            case LIKE ->  {return LikeUserNotificationResponse.of((ConvertedLikeNotification) notification);}
            case FOLLOW -> { return FollowUserNotificationResponse.of((ConvertedFollowNotification) notification);}
            default -> throw new IllegalArgumentException(
                    "Unsupported notification type: " + notification.getType());
        }
    }
}
