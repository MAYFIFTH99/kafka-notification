package fastcampus.consumer;

import fastcampus.event.LikeEvent;
import fastcampus.event.LikeEventType;
import fastcampus.task.LikeAddTask;
import fastcampus.task.LikeRemoveTask;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LikeEventConsumer {

    @Autowired
    private LikeAddTask likeAddTask;

    @Autowired
    private LikeRemoveTask likeRemoveTask;

    @Bean("like")
    public Consumer<LikeEvent> like(){
        return event -> {
            if (event.getType() == LikeEventType.ADD) {
                likeAddTask.processEvent(event);
            } else if(event.getType() == LikeEventType.REMOVE){
                likeRemoveTask.processEvent(event);
            }
        };
    }
}
