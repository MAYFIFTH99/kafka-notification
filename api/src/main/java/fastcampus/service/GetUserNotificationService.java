package fastcampus.service;

import fastcampus.service.dto.GetUserNotificationByPivotResult;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserNotificationService {

    private final NotificationListService listService;

    public void getUserNotificationByPivot(Long userId, Instant pivot){
        // 알림 목록 조회
        GetUserNotificationByPivotResult result = listService.getUserNotificationByPivot(
                userId, pivot);

        // 알림 목록 순회 DB 알림 -> 사용자 알림으로 변경

    }
}
