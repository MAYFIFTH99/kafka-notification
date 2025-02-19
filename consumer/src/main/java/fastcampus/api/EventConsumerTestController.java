package fastcampus.api;

import fastcampus.event.CommentEvent;
import fastcampus.event.LikeEvent;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventConsumerTestController implements EventConsumerTestControllerSpec{

    @Autowired
    private Consumer<CommentEvent> comment;

    @Autowired
    private Consumer<LikeEvent> like;

    @Override
    @PostMapping("/test/comment")
    public void comment(@RequestBody CommentEvent event) {
        comment.accept(event);
    }

    @Override
    @PostMapping("/test/like")
    public void like(@RequestBody LikeEvent event) {
        like.accept(event);
    }


}
