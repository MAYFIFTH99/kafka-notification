package fastcampus.service.converter;

import fastcampus.client.PostClient;
import fastcampus.client.UserClient;
import fastcampus.domain.LikeNotification;
import fastcampus.domain.Post;
import fastcampus.domain.User;
import fastcampus.service.dto.ConvertedLikeNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LikeUserNotificationConverter {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedLikeNotification convert(LikeNotification notification) {
        User user = userClient.getUser(notification.getLikerIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedLikeNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getLikerIds().size(),
                post.getImageUrl()
        );
    }

}
