package fastcampus.service;

import fastcampus.domain.CommentNotification;
import fastcampus.domain.FollowNotification;
import fastcampus.domain.LikeNotification;
import fastcampus.domain.NotificationType;
import fastcampus.service.converter.CommentUserNotificationConverter;
import fastcampus.service.converter.FollowUserNotificationConverter;
import fastcampus.service.converter.LikeUserNotificationConverter;
import fastcampus.service.dto.ConvertedNotification;
import fastcampus.service.dto.GetUserNotificationByPivotResult;
import fastcampus.service.dto.GetUserNotificationResult;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserNotificationService {

    private final NotificationListService listService;
    private final CommentUserNotificationConverter commentConverter;
    private final LikeUserNotificationConverter likeConverter;
    private final FollowUserNotificationConverter followConverter;

    public GetUserNotificationResult getUserNotificationByPivot(Long userId, Instant pivot){
        // 알림 목록 조회
        GetUserNotificationByPivotResult result = listService.getUserNotificationByPivot(
                userId, pivot);

        // 알림 목록 순회 DB 알림 -> 사용자 알림으로 변경
        List<ConvertedNotification> convertedNotifications = result.getNotifications().stream()
                .map(notification -> switch (notification.getType()) {
                    case NotificationType
                                 .COMMENT ->
                            commentConverter.convert((CommentNotification) notification);
                    case NotificationType.LIKE ->
                            likeConverter.convert((LikeNotification) notification);
                    case NotificationType.FOLLOW ->
                            followConverter.convert((FollowNotification) notification);
                }).toList();

        return new GetUserNotificationResult(
                convertedNotifications,
                result.isHasNext()
        );
    }
}
