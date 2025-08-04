package start.querydsl_start.entity.advancedMapping.composite.identify.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Parent {
    @Id
    @GeneratedValue
    @Column(name="PARENT_ID")
    private Long id;

    private String name;
}
