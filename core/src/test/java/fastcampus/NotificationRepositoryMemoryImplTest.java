package fastcampus;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBootApplication
class NotificationRepositoryMemoryImplTest {

    @Autowired
    private NotificationRepository sut;
    private final Instant now = Instant.now();
    private final Instant deletedAt = Instant.now().plus(90, ChronoUnit.DAYS);

    private final Notification notification = new CommentNotification("1", 1L, NotificationType.LIKE, now,
            now, now, deletedAt, 1L, 1L, "comment");

    /**
     * 알림 객체 생성
     * 저장
     * 조회
     */
    @Test
    void test_save() throws Exception {
        //given

        //when
        sut.save(notification);
        Optional<Notification> savedNotification = sut.findById("1");

        //then
        assertTrue(savedNotification.isPresent());
    }

    @Test
    void test_find_by_id() throws Exception {
        //given
        sut.save(notification);

        //when
        Optional<Notification> optionalNotification = sut.findById("1");
        Notification savedNotification = optionalNotification.orElseThrow();

        //then
        assertEquals(savedNotification.getId(), "1");
        assertEquals(savedNotification.userId, 1L);
        assertEquals(savedNotification.createdAt.getEpochSecond(), now.getEpochSecond());
        assertEquals(savedNotification.deletedAt.getEpochSecond(), deletedAt.getEpochSecond());
        assertEquals(savedNotification, notification);
    }

    @Test
    void test_delete_by_id() throws Exception {
        //given
        sut.save(notification);

        //when
        sut.deleteById("1");

        //then
        assertTrue(sut.findById("1").isEmpty());
    }
}