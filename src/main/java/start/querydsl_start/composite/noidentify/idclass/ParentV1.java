package start.querydsl_start.composite.noidentify.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Setter
@Getter
@IdClass(ParentV1Id.class)
@ToString(of={"id1","id2","name"})
public class ParentV1 {
    @Id
    @Column(name="PARENT_ID1")
    private Long id1;

    @Id
    @Column(name="PARENT_ID2")
    private Long id2;

    private String name;
}
