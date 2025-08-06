package start.querydsl_start.proxy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of ={"id","name","department"})
public class Employee {
    @Id
    @GeneratedValue
    @Column(name="EMPLOYEE_ID")
    private Long id;

    private String name;

    @ManyToOne
    private Department department;
}
