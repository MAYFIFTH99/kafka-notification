package fastcampus.response;

import fastcampus.domain.NotificationType;
import fastcampus.service.dto.ConvertedCommentNotification;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import lombok.Getter;

@Getter
@Schema(description = "댓글 알림 응답")
public class CommentUserNotificationResponse extends UserNotificationResponse {

    @Schema(description = "댓글을 남긴 사용자 이름")
    private final String userName;

    @Schema(description = "댓글을 남긴 사용자 프로필 이미지")
    private final String userProfileImageUrl;

    @Schema(description = "댓글 내용")
    private final String comment;

    @Schema(description = "게시글 이미지")
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
