package start.querydsl_start.realation.onetomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import start.querydsl_start.realation.manytoone.Subject;

@Entity
@Getter @Setter
@ToString(of = {"id","name"})
public class Attachment {
    @Id
    @GeneratedValue
    @Column(name = "ATTACHMENT_ID")
    private Long id;

    private String name;

}
