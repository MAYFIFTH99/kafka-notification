package fastcampus.response;

import fastcampus.domain.NotificationType;
import fastcampus.service.dto.ConvertedCommentNotification;
import java.time.Instant;
import lombok.Getter;

@Getter
public class CommentUserNotificationResponse extends UserNotificationResponse {
    private final String userName;
    private final String userProfileImageUrl;
    private final String comment;
    private final String postImageUrl;

    public CommentUserNotificationResponse(String id, NotificationType type,
            Instant occurredAt, String userName, String userProfileImageUrl, String comment,
            String postImageUrl) {
        super(id, type, occurredAt);
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.comment = comment;
        this.postImageUrl = postImageUrl;
    }

    public static CommentUserNotificationResponse of(ConvertedCommentNotification notification){
        return new CommentUserNotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getUserName(),
                notification.getUserProfileImageUrl(),
                notification.getComment(),
                notification.getPostImageUrl()
        );
    }
}
