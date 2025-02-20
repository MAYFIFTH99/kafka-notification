package fastcampus.controller;

import fastcampus.response.CheckNewNotificationResponse;
import fastcampus.service.CheckNewNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user-notifications")
@RequiredArgsConstructor
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec{

    private final CheckNewNotificationService service;

    @Override
    @GetMapping("/{userId}/new")
    public CheckNewNotificationResponse checkNew(@PathVariable  Long userId){
        boolean hasNew = service.checkNewNotification(userId);
        return new CheckNewNotificationResponse(hasNew);
    }

}
