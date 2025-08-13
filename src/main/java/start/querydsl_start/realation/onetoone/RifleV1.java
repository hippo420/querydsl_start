package start.querydsl_start.realation.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString(of={"id","code","name"})
public class RifleV1 {
    @Id
    @GeneratedValue
    @Column(name = "GUN_ID")
    private Long id;

    private String code;

    private String name;

    @OneToOne
    @JoinColumn(name = "SOLDIER_ID")
    private SoldierV1 soldier;
}
