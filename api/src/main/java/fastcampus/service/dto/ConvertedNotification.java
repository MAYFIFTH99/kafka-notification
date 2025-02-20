package fastcampus.service.dto;

import fastcampus.domain.NotificationType;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ConvertedNotification {
    protected String id;
    protected NotificationType type;
    protected Instant occurredAt;
    protected Instant lastUpdatedAt;
}
