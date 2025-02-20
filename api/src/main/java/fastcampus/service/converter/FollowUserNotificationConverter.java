package fastcampus.service.converter;

import fastcampus.client.UserClient;
import fastcampus.domain.FollowNotification;
import fastcampus.domain.User;
import fastcampus.service.dto.ConvertedFollowNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FollowUserNotificationConverter {

    private final UserClient userClient;

    public ConvertedFollowNotification convert(FollowNotification notification) {
        User user = userClient.getUser(notification.getFollowerId());
        Boolean isFollowing = userClient.getIsFollowing(notification.getUserId(),
                notification.getFollowerId());

        return new ConvertedFollowNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }

}
