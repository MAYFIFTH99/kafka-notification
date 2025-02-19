package fastcampus;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Document("notification")
@Getter
@Setter
public abstract class Notification {

    @Field(targetType = FieldType.STRING)
    protected String id;
    protected Long userId;
    protected NotificationType type;
    protected Instant occurredAt; // 알림 대상인 실제 이벤트가 발생한 시간
    protected Instant createdAt;
    protected Instant lastUpdatedAt;
    protected Instant deletedAt; // 알림이 삭제될 시간
}
