package start.querydsl_start.realation.manytoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of = {"id","name","subject"})
public class StudentV1 {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private Long id;

    private String name;

    //양방향 START
    @ManyToOne
    @JoinColumn(name="SUBJECT_ID")
    private SubjectV1 subject;

    public void setSubject(SubjectV1 subject) {
        this.subject = subject;

        if(!subject.getStudents().contains(this)){
            subject.getStudents().add(this);
        }
    }
}
