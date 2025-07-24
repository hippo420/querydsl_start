package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@TableGenerator(
        name="TABLE_SEQ_GENERATOR",
        table = "SEQ_TBL",
        pkColumnName ="SEQ_TYCD",
        pkColumnValue = "MEMBER_SEQ",
        allocationSize = 1
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "TABLE_SEQ_GENERATOR")
    @Column(name="memer_id")
    private Long id;

    private String name;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private Team team;

    public Member(String name, int age,Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public Member() {

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
