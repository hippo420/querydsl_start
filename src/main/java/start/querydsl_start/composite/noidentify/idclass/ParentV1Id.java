package start.querydsl_start.composite.noidentify.idclass;

import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString(of={"id1","id2"})
public class ParentV1Id implements Serializable {

    private Long id1;
    private Long id2;

    public ParentV1Id(){}

    public ParentV1Id(Long id1, Long id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof ParentV1Id that)) return false;
        return Objects.equals(id1, that.id1) && Objects.equals(id2, that.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }
}
