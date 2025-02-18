package fastcampus.event;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentEventConsumer {

    @Bean("comment")
    public Consumer<CommentEvent> comment(){
        return event -> log.info(event.toString());
    }
}
