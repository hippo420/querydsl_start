package start.querydsl_start.entity.advancedMapping.composite.noidentify.embed;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString( of = {"id","content"})
@Table(name = "COMMENT")
public class Comment {

    @EmbeddedId
    private CommentId id;

    @MapsId("boardId") // CommentId.boardId와 매핑
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column(name = "CONTENT")
    private String content;
}
