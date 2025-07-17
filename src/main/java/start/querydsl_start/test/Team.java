package start.querydsl_start.test;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter @Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy="team")
    private List<Member> member;

    public Team(String name) {
        this.name = name;
    }

    public Team() {

    }
}
