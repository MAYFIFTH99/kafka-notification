package fastcampus;

import java.time.Instant;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("LikeNotification")
public class LikeNotification extends Notification{

    private final Long postId;
    private final List<Long> likerIds;

    public LikeNotification(String id, Long userId, NotificationType type, Instant occurredAt,
            Instant createdAt, Instant lastUpdatedAt, Instant deletedAt, Long postId,
            List<Long> likerIds) {
        super(id, userId, type, occurredAt, createdAt, lastUpdatedAt, deletedAt);
        this.postId = postId;
        this.likerIds = likerIds;
    }

    public void addLiker(Long likerId, Instant occurredAt, Instant now, Instant retention){
        likerIds.add(likerId);
        setOccurredAt(occurredAt);
        setLastUpdatedAt(now);
        setDeletedAt(retention);
    }

    public void removeLiker(Long userId, Instant now){
        likerIds.remove(userId);
        setLastUpdatedAt(now);
    }
}
