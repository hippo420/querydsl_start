package start.querydsl_start.entity.manytoone;

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


    // 양방향 START
//    @OneToMany(mappedBy = "subject")
//    private List<Student> students = new ArrayList<Student>();
//
//    public void addStudent(Student student) {
//        this.students.add(student);
//        if(student.getSubject() != this)
//        {
//            student.setSubject(this);
//        }
//    }
    // 양방향 END
}
