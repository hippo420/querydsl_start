package start.querydsl_start.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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


    //다대다 관계
    @ManyToMany
    @JoinTable(name="MEMBER_PRODUCT",
                joinColumns = @JoinColumn(name="MEMBER_ID"),
                inverseJoinColumns = @JoinColumn(name="PRODUCT_ID"))
    private List<Product> products = new ArrayList<>();

    //연관관계 편의 메소드
    public void setTeam(Team team) {
        //기존팀 관계 제거
        if(this.team != null){
            //관계제거
            this.team.getMember().remove(this);
        }
        //새로운 팀 및 관계추가
        this.team = team;
        team.getMember().add(this);
    }

    public Member(String name, int age,Team team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
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
