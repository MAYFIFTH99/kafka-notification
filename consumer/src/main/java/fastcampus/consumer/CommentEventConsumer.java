package fastcampus.consumer;

import fastcampus.event.CommentEvent;
import fastcampus.event.CommentEventType;
import fastcampus.task.CommentAddTask;
import fastcampus.task.CommentRemoveTask;
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

    @Autowired
    private CommentRemoveTask commentRemoveTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment(){
        return event -> {
            if (event.getType() == CommentEventType.ADD) {
                commentAddTask.processEvent(event);
            }else if(event.getType() == CommentEventType.REMOVE){
                commentRemoveTask.processEvent(event);
            }
        };
    }
}
