package fastcampus.consumer;

import fastcampus.event.FollowEvent;
import fastcampus.event.FollowEventType;
import fastcampus.task.FollowAddTask;
import fastcampus.task.FollowRemoveTask;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FollowEventConsumer {

    @Autowired
    private FollowAddTask followAddTask;

    @Autowired
    private FollowRemoveTask followRemoveTask;

    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            if (event.getType() == FollowEventType.ADD) {
                followAddTask.processEvent(event);
            } else if (event.getType() == FollowEventType.REMOVE) {
                followRemoveTask.processEvent(event);
            }
        };
    }
}
