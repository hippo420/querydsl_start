package start.querydsl_start.constuct;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)

public class OneMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="memer_id")
    private Long id;

    private String name;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private OneTeam team;

    public OneMember(String name, int age, OneTeam team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public OneMember() {

    }

    @Override
    public String toString() {
        return "Member{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
