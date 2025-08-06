package start.querydsl_start.proxy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of ={"id","name"})
public class Department {
    @Id
    @GeneratedValue
    @Column(name="DEPARTMENT_ID")
    private Long id;

    private String name;
}
