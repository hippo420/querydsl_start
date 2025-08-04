package start.querydsl_start.entity.advancedMapping.composite.identify.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChildId implements Serializable {

    private Long parentId;

    @Column(name="CHILD_ID")
    private Long parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildId childId)) return false;
        return Objects.equals(parentId, childId.parentId) && Objects.equals(parent, childId.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, parent);
    }
}
