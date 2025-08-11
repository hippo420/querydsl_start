package start.querydsl_start.realation.manytoone;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@ToString(of = {"id","name","subject"})
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private Long id;

    private String name;

    // 단방향 START
    @ManyToOne
    @JoinColumn(name="SUBJECT_ID")
    private Subject subject;
}
