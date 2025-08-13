package start.querydsl_start.realation.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of={"id","name","rifle"})
public class SoldierV1 {
    @Id
    @GeneratedValue
    @Column(name = "SOLDIER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "soldier")
    private RifleV1 rifle;

    public void setRifle(RifleV1 rifle) {
        this.rifle = rifle;
        if (rifle != null) {
            rifle.setSoldier(this); // 양방향 세팅
        }
    }
}
