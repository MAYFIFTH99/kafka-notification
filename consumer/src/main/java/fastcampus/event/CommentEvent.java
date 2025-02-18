package fastcampus.event;

import lombok.Data;

@Data
public class CommentEvent {

    /**
     * 어떤 유저가 어떤 게시글에 어떤 댓글을 추가/삭제 했는지에 대한 이벤트 관리
     */
    private CommentEventType type; // ADD, REMOVE
    private Long postId; // 게시글 ID
    private Long userId; // 유저 ID
    private Long commentId; // 댓글 ID
}
