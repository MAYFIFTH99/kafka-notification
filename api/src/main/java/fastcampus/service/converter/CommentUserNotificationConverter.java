package fastcampus.service.converter;

import fastcampus.client.PostClient;
import fastcampus.client.UserClient;
import fastcampus.domain.CommentNotification;
import fastcampus.domain.Post;
import fastcampus.domain.User;
import fastcampus.service.dto.ConvertedCommentNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentUserNotificationConverter {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedCommentNotification convert(CommentNotification notification) {
        User user = userClient.getUser(notification.getUserId());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedCommentNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getComment(),
                post.getImageUrl()
        );
    }
}
