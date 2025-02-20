package fastcampus.response;

import fastcampus.service.dto.GetUserNotificationResult;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotificationListResponse {
    private List<UserNotificationResponse> notifications;
    private boolean hasNext;
    private Instant pivot;

    public static UserNotificationListResponse of(GetUserNotificationResult result) {
        // ConvertedNotification -> UserNotificationResponse
        List<UserNotificationResponse> notifications = result.getNotifications().stream()
                .map(UserNotificationResponse::of)
                .toList();

        // pivot 생성
        Instant pivot = (result.isHasNext() && !notifications.isEmpty())
                ? notifications.getLast().getOccurredAt() : null;

        return new UserNotificationListResponse(
                notifications,
                result.isHasNext(),
                pivot
        );


    }
}
