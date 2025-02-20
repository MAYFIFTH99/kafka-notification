package fastcampus.service.dto;

import fastcampus.domain.Notification;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class GetUserNotificationByPivotResult {

    private List<Notification> notifications;
    private boolean hasNext;
}
