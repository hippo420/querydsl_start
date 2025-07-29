package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@Slf4j
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
@SequenceGenerator(
        name="TEAM_SEQ_GENERATOR",
        sequenceName = "TEAM_SEQ",
        initialValue = 1,
        allocationSize = 20
)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_SEQ_GENERATOR")
    private Long id;

    private String name;

    @OneToMany(mappedBy="team")
    private List<Member> member = new ArrayList<>();

    public Team(String name) {
        System.out.println(this.getClass().getName()+" 의 커스텀생성자 입니다.");
        this.name = name;
    }

    public Team() {
        System.out.println(this.getClass().getName()+" 의 기본생성자 입니다.");
    }
}
