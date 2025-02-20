package fastcampus.response;

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
}
