package fastcampus.service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserNotificationResult {

    private List<ConvertedNotification> notifications;
    private boolean hasNext;



}
