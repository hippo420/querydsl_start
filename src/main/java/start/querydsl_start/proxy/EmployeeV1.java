package start.querydsl_start.proxy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of ={"id","name","department"})
public class EmployeeV1 {
    @Id
    @GeneratedValue
    @Column(name="EMPLOYEE_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DEPARTMENT_ID", nullable = true)
    private Department department;
}
