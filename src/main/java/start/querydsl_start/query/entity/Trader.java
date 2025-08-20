package start.querydsl_start.query.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter
@ToString(of={"id","username","age","firm"})
public class Trader {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name="firm")
    private Firm firm;

}
