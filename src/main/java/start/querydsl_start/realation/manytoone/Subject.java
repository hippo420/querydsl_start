package start.querydsl_start.realation.manytoone;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter
@ToString(of = {"id","name"})
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "SUBJECT_ID")
    private Long id;
    private String name;

}
