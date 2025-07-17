package start.querydsl_start.test;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter @Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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
