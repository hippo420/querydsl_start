package start.querydsl_start.entity.advancedMapping.composite.noidentify.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommentId implements Serializable {

    @Column(name="BOARD_ID")
    private Long id1;

    @Column(name = "COMMENT_ID")
    private Long id2;

    public CommentId() {}

    public CommentId(Long id1, Long id2) {
        this.id1 = id2;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommentId commentId = (CommentId) o;
        return Objects.equals(id1, commentId.id1) && Objects.equals(id2, commentId.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }
}
