package fastcampus;

import static fastcampus.domain.NotificationType.COMMENT;
import static org.junit.jupiter.api.Assertions.*;

import fastcampus.domain.CommentNotification;
import fastcampus.domain.Notification;
import fastcampus.domain.NotificationType;
import fastcampus.repository.NotificationRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@SpringBootTest
@SpringBootApplication
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository repository;

    private final Long userId = 2L;
    private final Long postId = 3L;
    private final Long writerId = 4L;
    private final Long commentId = 5L;
    private final String comment = "comment";
    private final Instant now = Instant.now();
    private final Instant deletedAt = Instant.now().plus(90, ChronoUnit.DAYS);

    private final Notification notification = new CommentNotification("1", 1L, NotificationType.LIKE, now,
            now, now, deletedAt, 1L, 1L, "comment", 1L);

    @BeforeEach
    void setUp(){
        for (int i = 1; i <= 5; i++) {
            Instant occurredAt = now.minus(i, ChronoUnit.DAYS);
            repository.save(
                    new CommentNotification(
                            "id-" + i, userId,
                            COMMENT, occurredAt,
                            now, now,
                            deletedAt, postId,
                            writerId, comment, commentId));
        }
    }

    @AfterEach
    void tearDown(){
        repository.deleteAll();
    }

    /**
     * 알림 객체 생성
     * 저장
     * 조회
     */
    @Test
    void test_save() throws Exception {
        //given

        //when
        repository.save(notification);
        Optional<Notification> savedNotification = repository.findById("1");

        //then
        assertTrue(savedNotification.isPresent());
    }

    @Test
    void test_find_by_id() throws Exception {
        //given
        repository.save(notification);

        //when
        Optional<Notification> optionalNotification = repository.findById("1");
        Notification savedNotification = optionalNotification.orElseThrow();

        //then
        assertEquals(savedNotification.getId(), "1");
        assertEquals(savedNotification.getUserId(), 1L);
        assertEquals(savedNotification.getCreatedAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(savedNotification.getDeletedAt().getEpochSecond(), deletedAt.getEpochSecond());
        assertEquals(savedNotification, notification);
    }

    @Test
    void test_delete_by_id() throws Exception {
        //given
        repository.save(notification);

        //when
        repository.deleteById("1");

        //then
        assertTrue(repository.findById("1").isEmpty());
    }

    @Test
    void test_findAllByUserIdOrderByOccurredAtDesc() throws Exception {
        Pageable pageable = PageRequest.of(0, 3);
        //given

        //when
        Slice<Notification> result = repository.findAllByUserIdOrderByOccurredAtDesc(userId,
                pageable);

        //then
        assertEquals(result.getContent().size(), 3);
        assertTrue(result.hasNext());

        Notification first = result.getContent().get(0);
        Notification second = result.getContent().get(1);
        Notification third = result.getContent().get(2);

        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));
        assertTrue(second.getOccurredAt().isAfter(third.getOccurredAt()));
    }

    @Test
    void test_findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc_firstQuery() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 3);

        //when
        Slice<Notification> result = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(
                userId, now, pageable);

        //then
        Notification first = result.getContent().get(0);
        Notification second = result.getContent().get(1);
        Notification third = result.getContent().get(2);

        assertTrue(first.getOccurredAt().isAfter(second.getOccurredAt()));
        assertTrue(second.getOccurredAt().isAfter(third.getOccurredAt()));
    }

    @Test
    void test_findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc_secondQuery() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 3);

        //when
        Slice<Notification> firstResult = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(
                userId, now, pageable);
        Notification last = firstResult.getContent().get(2);

        Instant pivot = last.getOccurredAt();

        Slice<Notification> secondResult = repository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(
                userId, pivot, pageable);

        //then
        assertEquals(secondResult.getContent().size(), 2);
        assertFalse(secondResult.hasNext());
    }
}