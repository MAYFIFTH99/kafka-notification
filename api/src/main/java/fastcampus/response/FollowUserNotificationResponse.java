package fastcampus.response;

import fastcampus.domain.NotificationType;
import fastcampus.service.dto.ConvertedFollowNotification;
import java.time.Instant;
import lombok.Getter;

@Getter
public class FollowUserNotificationResponse extends UserNotificationResponse {

    private final String userName;
    private final String userProfileImageUrl;
    private final boolean isFollowing;


    public FollowUserNotificationResponse(String id, NotificationType type,
            Instant occurredAt, String userName, String userProfileImageUrl, boolean isFollowing) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isFollowing = isFollowing;
    }

    public static FollowUserNotificationResponse of(ConvertedFollowNotification notification) {
        return new FollowUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.isFollowing()
        );
    }
}
