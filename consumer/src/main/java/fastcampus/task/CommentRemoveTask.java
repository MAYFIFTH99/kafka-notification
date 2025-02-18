package fastcampus.task;

import fastcampus.NotificationGetService;
import fastcampus.NotificationRemoveService;
import fastcampus.NotificationType;
import fastcampus.Post;
import fastcampus.PostClient;
import fastcampus.event.CommentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentRemoveTask {

    @Autowired
    private PostClient postClient;

    @Autowired
    NotificationGetService getService;

    @Autowired
    NotificationRemoveService removeService;

    public void processEvent(CommentEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (post.getUserId().equals(event.getUserId())) {
            return;
        }

        getService.getNotification(NotificationType.COMMENT, event.getCommentId())
                .ifPresentOrElse(
                        notification -> removeService.deleteById(notification.getId()),
                                () -> log.error("Notification not found")
                );

    }

}
