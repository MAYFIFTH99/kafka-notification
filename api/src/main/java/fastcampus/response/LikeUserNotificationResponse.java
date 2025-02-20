package fastcampus.response;

import fastcampus.domain.NotificationType;
import fastcampus.service.dto.ConvertedLikeNotification;
import java.time.Instant;
import lombok.Getter;

@Getter
public class LikeUserNotificationResponse extends UserNotificationResponse {
    private final String userName;
    private final String userProfileImageUrl;
    private final long userCount;
    private final String postImageUrl;

    public LikeUserNotificationResponse(String id, NotificationType type,
            Instant occurredAt, String userName, String userProfileImageUrl, long userCount,
            String postImageUrl) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userCount = userCount;
        this.postImageUrl = postImageUrl;
    }

    public static LikeUserNotificationResponse of(ConvertedLikeNotification notification) {
        return new LikeUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.getUserCount(),
                notification.getPostImageUrl());
    }
}
