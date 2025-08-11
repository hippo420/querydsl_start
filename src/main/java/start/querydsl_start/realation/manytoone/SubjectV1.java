package start.querydsl_start.realation.manytoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@ToString(of = {"id","name"})
public class SubjectV1 {
    @Id
    @GeneratedValue
    @Column(name = "SUBJECT_ID")
    private Long id;

    private String name;

    // 양방향 START
    @OneToMany(mappedBy = "subject")
    private List<StudentV1> students = new ArrayList<StudentV1>();

    public void addStudent(StudentV1 student) {
        this.students.add(student);
        if(student.getSubject() != this)
        {
            student.setSubject(this);
        }
    }
}
