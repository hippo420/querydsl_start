package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Soldier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="SOLDIER_ID")
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne
    @JoinColumn(name = "GUN_ID")
    private Gun gun;

}
