package fastcampus.domain;

import java.time.Instant;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("FollowNotification")
public class FollowNotification extends Notification{

    private final Long followerId;

    public FollowNotification(String id, Long userId, NotificationType type,
            Instant occurredAt, Instant createdAt, Instant lastUpdatedAt,
            Instant deletedAt, Long followerId) {
        super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deletedAt);
        this.followerId = followerId;
    }
}
