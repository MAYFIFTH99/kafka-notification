package fastcampus.event;

import fastcampus.task.CommentAddTask;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentEventConsumer {

    @Autowired
    private CommentAddTask commentAddTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment(){
        return event -> {
            if (event.getType() == CommentEventType.ADD) {
                commentAddTask.processEvent(event);

            }
        };
    }
}
