package fastcampus.task;

import fastcampus.domain.Comment;
import fastcampus.client.CommentClient;
import fastcampus.domain.CommentNotification;
import fastcampus.domain.Notification;
import fastcampus.utils.NotificationIdGenerator;
import fastcampus.service.NotificationSaveService;
import fastcampus.domain.NotificationType;
import fastcampus.domain.Post;
import fastcampus.client.PostClient;
import fastcampus.event.CommentEvent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentAddTask {

    @Autowired
    private PostClient postClient;

    @Autowired
    private CommentClient commentClient;

    @Autowired
    NotificationSaveService saveService;

    public void processEvent(CommentEvent event) {
        // 내가 작성한 댓글인 경우 무시
        Post post = postClient.getPost(event.getPostId());
        if (post.getUserId().equals(event.getUserId())) {
            return;
        }
        Comment comment = commentClient.getComment(event.getCommentId());

        // 알림 생성
        Notification notification = createNotification(post, comment);

        // 알림 저장
        saveService.insert(notification);

    }

    private Notification createNotification(Post post, Comment comment){
        Instant now = Instant.now();
        return new CommentNotification(
                NotificationIdGenerator.generate(),
                post.getUserId(),
                NotificationType.COMMENT,
                comment.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                post.getId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getId()
        );
    }


}
