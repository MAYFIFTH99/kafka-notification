package fastcampus.controller;

import fastcampus.response.SetLastReadAtResponse;
import fastcampus.service.LastReadAtService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user-notifications")
public class NotificationReadController implements NotificationReadControllerSpec{

    private final LastReadAtService service;

    @PutMapping("/{userId}/read")
    public SetLastReadAtResponse setLastReadAt(@PathVariable Long userId){
        Instant lastReadAt = service.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }

}
