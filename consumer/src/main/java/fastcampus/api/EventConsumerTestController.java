package fastcampus.api;

import fastcampus.event.CommentEvent;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventConsumerTestController {

    @Autowired
    private Consumer<CommentEvent> comment;

    @PostMapping("/test/comment")
    public void comment(CommentEvent event) {
        comment.accept(event);
    }


}
