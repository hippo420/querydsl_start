package start.querydsl_start.entity.manytoone;

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
    // 단방향 END

    // 양방향 START
//    @ManyToOne
//    @JoinColumn(name="SUBJECT_ID")
//    private Subject subject;
//
//    public void setSubject(Subject subject) {
//        this.subject = subject;
//
//        if(!subject.getStudents().contains(this)){
//            subject.getStudents().add(this);
//        }
//    }
    // 양방향 END
}
