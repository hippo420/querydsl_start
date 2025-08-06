package start.querydsl_start.inherit.superclass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Setter @Getter
@ToString(of = {"id","name","company"})
public abstract class BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String company;
}
