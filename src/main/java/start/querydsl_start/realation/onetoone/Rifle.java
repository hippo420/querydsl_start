package start.querydsl_start.realation.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of={"id","code","name"})
public class Rifle {

    @Id
    @GeneratedValue
    @Column(name = "SOLDIER_ID")
    private Long id;

    private String code;

    private String name;
}
