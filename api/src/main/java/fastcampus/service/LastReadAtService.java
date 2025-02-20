package fastcampus.service;

import fastcampus.repository.NotificationReadRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LastReadAtService {

    private final NotificationReadRepository repository;

    public Instant setLastReadAt(long userId){
        return repository.setLastReadAt(userId);
    }

}
