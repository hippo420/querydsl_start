package start.querydsl_start.entity.advancedMapping.composite.identify.embed;

import jakarta.persistence.*;

@Entity
public class Child {
    @EmbeddedId
    private ChildId id;

    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name="PARENT_ID")
    private Parent parent;

    private String name;
}
