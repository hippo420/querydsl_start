package start.querydsl_start.composite.identify.idclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@IdClass(PersonId.class)
@ToString(of={"id","name","age"})
public class Person {

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="FLIM_ID"),
            @JoinColumn(name="ACTOR_ID")
    })
    private Actor Actor;


    @Id
    @Column(name="PERSON_ID")
    private Long id;

    private String name;
    private int age;
}
