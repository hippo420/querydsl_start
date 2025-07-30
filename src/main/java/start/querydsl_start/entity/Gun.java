package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gun {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GUN_ID")
    private Long id;

    @Column(name="name")
    private String name;

    //양방향 관계
    @OneToOne(mappedBy = "gun")
    private Soldier soldier;
}
