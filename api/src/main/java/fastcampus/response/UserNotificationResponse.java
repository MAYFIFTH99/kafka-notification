package fastcampus.response;

import fastcampus.domain.NotificationType;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class UserNotificationResponse {
    private String id;
    private NotificationType type;
    private Instant occurredAt;
}
