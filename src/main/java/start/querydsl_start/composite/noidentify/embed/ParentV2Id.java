package start.querydsl_start.composite.noidentify.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
@Embeddable
@ToString(of={"id1","id2"})
public class ParentV2Id implements Serializable {
    @Column(name="PARENT_ID1")
    private Long id1;

    @Column(name="PARENT_ID2")
    private Long id2;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParentV2Id that = (ParentV2Id) o;
        return Objects.equals(id1, that.id1) && Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }

    public ParentV2Id() {}

    public ParentV2Id(Long id1, Long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }
}
