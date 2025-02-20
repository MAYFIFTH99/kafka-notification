package fastcampus.controller;

import fastcampus.response.UserNotificationListResponse;
import fastcampus.service.GetUserNotificationService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user-notification")
public class UserNotificationListController {

    private final GetUserNotificationService getUserNotificationService;

    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(@PathVariable Long userId,
            @RequestParam(required = false) Instant pivot) {
        return UserNotificationListResponse.of(getUserNotificationService.getUserNotificationByPivot(
                userId, pivot));
    }
}
