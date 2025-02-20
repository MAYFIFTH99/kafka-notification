package fastcampus.service;

import fastcampus.repository.NotificationRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationListService {
    private static final int PAGE_SIZE = 20;

    private final NotificationRepository notificationRepository;

    // 목록 조회 : Pivot 방식(occurredAt 기준) vs Paging 방식(page size, number) 중 피봇 방식
    public void getUserNotificationByPivot(Long userId, Instant occurredAt){
        if(occurredAt == null){
            notificationRepository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        }else{
            notificationRepository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }
    }

}
