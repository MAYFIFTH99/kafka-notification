package fastcampus.service;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckNewNotificationService {

    private final NotificationGetService getService;
    private final LastReadAtService lastReadAtService;

    public boolean checkNewNotification(Long userId) {
        // lastReadAt vsv latestUpdateAt
        Instant latestUpdatedAt = getService.getLatestUpdatedAt(userId);
        if(latestUpdatedAt == null) {
            return false;
        }

        Instant lastReadAt = lastReadAtService.getLastReadAt(userId);
        if(lastReadAt == null) {
            return true;
        }

        return latestUpdatedAt.isAfter(lastReadAt);

    }
}
