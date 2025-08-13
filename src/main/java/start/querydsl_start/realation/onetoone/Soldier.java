package start.querydsl_start.realation.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Getter
@Setter
@ToString(of={"id","name","rifle"})
public class Soldier {
    @Id
    @GeneratedValue
    @Column(name = "SOLDIER_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "RIFLE_ID")
    private Rifle rifle;
}
